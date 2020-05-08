package com.shoumei.xhn.http

import com.shoumei.xhn.R
import com.shoumei.xhn.SmApplication
import com.shoumei.xhn.utils.LogUtil
import com.shoumei.xhn.utils.NetworkUtils
import com.shoumei.xhn.utils.ToastUtil
import com.google.gson.Gson
import com.shoumei.xhn.base.BaseModel
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.json.JSONObject
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import java.io.File
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException


/**
 *
 * Created by 张野 on 2017/9/28.
 */
object ApiManager {
    private lateinit var apiService: ApiService
    private lateinit var retrofit: Retrofit
    private lateinit var okHttpClient: OkHttpClient
    private val gSon = Gson()

    const val STRING = 0X1
    const val OTHER = 0x2

    init {
        init()
    }


    private fun getHost(): String = if (Constant.isDebug()) "http://dev.api.shoumeiapp.com" else "http://api.shoumeiapp.com"


    fun init() {
        /*
        * 初始化OkHttpClient
        */
        okHttpClient = OkHttpClient.Builder()
                .addInterceptor(HeaderInterceptor())
                .addNetworkInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build()

        /*
         * 初始化Retrofit
         */
        retrofit = Retrofit.Builder()
                .baseUrl(getHost())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build()
        apiService = retrofit.create(ApiService::class.java)
    }


    /**
     * 发起一个网络请求并解析成实体类
     */
    private fun <T> request(composites: CompositeDisposable?, observable: Observable<ResponseBody>, onResult: OnResult<T>) {
        if (!NetworkUtils.isNetworkAvaliable()) {
            onResult.onFailed("0", SmApplication.getApp().getString(R.string.networkError))
            return
        }
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(object : Observer<ResponseBody> {

                    override fun onNext(data: ResponseBody) {
                        val body = data.string()
                        parserJson(body, onResult)
                    }

                    override fun onError(e: Throwable) {
                        e.printStackTrace()

                        var code = "0"
                        var message = ""

                        if (e is SocketTimeoutException || e is TimeoutException) {
                            message = SmApplication.getApp().getString(R.string.timeoutError)
                            onResult.onFailed(code, message)
                        }

                        if (e is ConnectException) {
                            message = SmApplication.getApp().getString(R.string.connectError)
                            onResult.onFailed(code, message)
                        }

                        if (e is HttpException) {
                            code = e.code().toString()
                            message = e.message()
                            onResult.onFailed(code, message)
                        }
                        ToastUtil.show(message)
                    }

                    override fun onComplete() {

                    }

                    override fun onSubscribe(disposable: Disposable) {
                        composites?.add(disposable)
                    }

                })
    }


    /**
     * GET请求
     */
    fun <T> get(composites: CompositeDisposable?, params: Map<String, String>?, url: String, onResult: OnResult<T>) {
        if (params != null) {
            val sb = StringBuilder("?")
            params.forEach {
                sb.append("${it.key}=${it.value}&")
            }
            sb.deleteCharAt(sb.length - 1)
            request(composites, apiService.get(url + sb.toString()), onResult)
        } else {
            request(composites, apiService.get(url), onResult)
        }
    }


    /**
     *  Post请求
     */
    fun <T> post(composites: CompositeDisposable?, params: Map<String, String>, url: String, onResult: OnResult<T>) {
        request(composites, apiService.post(params, url), onResult)
    }


    /**
     * Post一个JSON
     */
    fun <T> postJson(composites: CompositeDisposable?, params: String, url: String, onResult: OnResult<T>) {
        val requestBody = RequestBody.create(MediaType.parse("application/json"), params)
        request(composites, apiService.post(requestBody, url), onResult)
    }


    /**
     * Post一张图片
     */
    fun <T> postImage(composites: CompositeDisposable?, path: String, onResult: OnResult<T>) {
        val requestBodyBuilder = MultipartBody.Builder().setType(MultipartBody.FORM)
        val file = File(path)
        requestBodyBuilder.addFormDataPart("file", path,
                RequestBody.create(MediaType.parse("image/jpeg"), file))
        requestBodyBuilder.addFormDataPart("picturetype", "jpg")
        request(composites, apiService.post(requestBodyBuilder.build(), Constant.COMMON_UPLOADS), onResult)
    }


    /**
     * 结果回调
     */
    abstract class OnResult<in T> {
        lateinit var typeToken: Type
        var tag: Int

        init {
            val t = javaClass.genericSuperclass
            val args = (t as ParameterizedType).actualTypeArguments
            val type = "class java.lang.String"
            if (args[0].toString() == type) {
                tag = STRING
            } else {
                typeToken = args[0]
                tag = OTHER
            }
        }

        abstract fun onSuccess(data: T)
        abstract fun onFailed(code: String, message: String)
    }

    fun getHttpClient(): OkHttpClient = okHttpClient


    @Suppress("UNCHECKED_CAST")
    private fun <T> parserJson(json: String, onResult: OnResult<T>) {
        if (onResult.tag == STRING) {
            onResult.onSuccess(json as T)
        } else {
            if (onResult.typeToken.toString().contains(BaseModel::class.java.name)) {
                try {
                    val jsonObj = JSONObject(json)
                    val status = jsonObj.opt("status").toString()
                    if (status == "200") {
                        val baseModel = gSon.fromJson<T>(json, onResult.typeToken)
                        onResult.onSuccess(baseModel)
                    } else {
                        val message = jsonObj.opt("message").toString()
                        ToastUtil.show(message)
                        onResult.onFailed(status, message)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            } else {
                onResult.onSuccess(gSon.fromJson(json, onResult.typeToken))
            }
        }
    }
}