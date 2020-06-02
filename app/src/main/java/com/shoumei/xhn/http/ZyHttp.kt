package com.shoumei.xhn.http

import com.shoumei.xhn.http.bean.HttpResultBean
import com.shoumei.xhn.http.parser.GSonResponseParser
import com.shoumei.xhn.http.parser.IResponseParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.util.concurrent.TimeUnit

/**
 * Desc
 * Author 张野
 * Mail zhangye98@foxmail.com
 * Date 2020/4/28 02:07
 */
@Suppress("UNCHECKED_CAST", "MemberVisibilityCanBePrivate")
object ZyHttp {

    //请求创建器
    private var zyRequest = ZyRequest()

    //结果解析器（默认为Gson）
    var iResponseParser: IResponseParser = GSonResponseParser()

    //okHttpClient初始化
    var okHttpClient: OkHttpClient = OkHttpClient.Builder().apply {
        addInterceptor(HeaderInterceptor())
        addNetworkInterceptor(
                HttpLoggingInterceptor(HttpLoggingInterceptor.Logger {
                    Platform.get().log(Platform.WARN, it, null)
                }).apply {
                    level = Level.BODY
                })
        connectTimeout(10000L, TimeUnit.MILLISECONDS) //连接超时时间
        readTimeout(10000L, TimeUnit.MILLISECONDS) //读取超时时间
    }.build()


    /**
     * get请求
     * @param url URL服务器地址
     * @param params 传递的数据map（key,value)
     * @param onResult 解析类型 例如为String::class.java
     * @return HttpResultBean<clazz> 网络请求结果
     */
    suspend fun <T> get(url: String, params: HashMap<String, String>?, onResult: OnResult<T>): HttpResultBean<T> {
        return withContext(Dispatchers.IO) {
            //创建okHttp请求
            val request = zyRequest.getRequest(url, params)
            return@withContext execution(request, onResult)
        }
    }


    /**
     * postForm请求
     * @param url URL服务器地址
     * @param params 传递的数据map（key,value)
     * @param clazz 解析类型 例如为String::class.java
     * @return HttpResultBean<clazz> 网络请求结果
     */
    suspend fun <T> post(url: String, params: HashMap<String, String>?, onResult: OnResult<T>): HttpResultBean<T> {
        return withContext(Dispatchers.IO) {
            //创建okHttp请求
            val request = zyRequest.postFormRequest(url, params)
            return@withContext execution(request, onResult)
        }
    }


    /**
     * post传递JSON请求
     * @param url URL服务器地址
     * @param json 传递的json字符串
     * @param clazz 解析类型 例如为String::class.java
     * @return HttpResultBean<clazz> 网络请求结果
     */
    suspend fun <T> postJson(url: String, json: String, onResult: OnResult<T>): HttpResultBean<T> {
        return withContext(Dispatchers.IO) {
            //创建okHttp请求
            val request = zyRequest.postJsonRequest(url, json)
            return@withContext execution(request, onResult)
        }
    }


    /**
     * 执行网络请求并处理结果
     * @param request OkHttp请求对象
     * @param onResult 网络请求回调
     * @return HttpResultBean<clazz> 网络请求结果
     */
    @Suppress("BlockingMethodInNonBlockingContext")
    private suspend fun <T> execution(request: Request, onResult: OnResult<T>): HttpResultBean<T> {
        //创建请求结果对象
        val httpResultBean = HttpResultBean<T>()
        try {
            //执行异步网络请求
            val response = okHttpClient.newCall(request).execute()

            //获取HTTP状态码
            httpResultBean.httpCode = response.code()
            //获取Response回执信息
            httpResultBean.msg = response.message()


            withContext(Dispatchers.Main) {
                onResult.onComplete()
                //请求成功进行解析
                if (response.isSuccessful) {
                    response.body()?.let {
                        httpResultBean.bean = iResponseParser.parserResponse<T>(it, onResult.typeToken, onResult.serializedName)
                        onResult.onSuccess(httpResultBean.bean as T)

                    }
                }
            }

        } catch (e: Exception) {
            //出现异常获取异常信息
            httpResultBean.exception = e
            e.printStackTrace()
            withContext(Dispatchers.Main) {
                onResult.onFailed(httpResultBean.httpCode.toString(), httpResultBean.msg ?: "")
            }
        }
        //返回请求结果
        return httpResultBean
    }
}