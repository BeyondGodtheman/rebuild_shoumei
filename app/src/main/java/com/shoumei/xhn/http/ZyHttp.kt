package com.shoumei.xhn.http

import com.shoumei.xhn.http.bean.HttpResultBean
import com.shoumei.xhn.http.parser.GSonResponseParser
import com.shoumei.xhn.http.parser.IResponseParser
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import java.util.concurrent.TimeUnit

/**
 * Desc
 * Author ZhangYe
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
        addNetworkInterceptor(HttpLoggingInterceptor().apply {
            level = Level.BODY
        })
        connectTimeout(10000L, TimeUnit.MILLISECONDS) //连接超时时间
        readTimeout(10000L, TimeUnit.MILLISECONDS) //读取超时时间
    }.build()


    /**
     * get请求
     * @param url URL服务器地址
     * @param params 传递的数据map（key,value)
     * @param clazz 解析类型 例如为String::class.java
     * @return HttpResultBean<clazz> 网络请求结果
     */
    suspend fun <T> get(url: String, params: HashMap<String, String>?, clazz: Class<T>): HttpResultBean<T> {
        return withContext(Dispatchers.IO) {
            //创建okHttp请求
            val request = zyRequest.getRequest(url, params)
            return@withContext parserResult(request, clazz)
        }
    }


    /**
     * postForm请求
     * @param url URL服务器地址
     * @param params 传递的数据map（key,value)
     * @param clazz 解析类型 例如为String::class.java
     * @return HttpResultBean<clazz> 网络请求结果
     */
    suspend fun <T> post(url: String, params: HashMap<String, String>?, clazz: Class<T>): HttpResultBean<T> {
        return withContext(Dispatchers.IO) {
            //创建okHttp请求
            val request = zyRequest.postFormRequest(url, params)
            return@withContext parserResult(request, clazz)
        }
    }


    /**
     * post传递JSON请求
     * @param url URL服务器地址
     * @param json 传递的json字符串
     * @param clazz 解析类型 例如为String::class.java
     * @return HttpResultBean<clazz> 网络请求结果
     */
    suspend fun <T> postJson(url: String, json: String, clazz: Class<T>): HttpResultBean<T> {
        return withContext(Dispatchers.IO) {
            //创建okHttp请求
            val request = zyRequest.postJsonRequest(url, json)
            return@withContext parserResult(request, clazz)
        }
    }


    /**
     * 执行网络请求并处理结果
     * @param request OkHttp请求对象
     * @param clazz 解析类型 例如为String::class.java
     * @return HttpResultBean<clazz> 网络请求结果
     */
    private fun <T> parserResult(request: Request, clazz: Class<T>): HttpResultBean<T> {
        //创建请求结果对象
        val httpResultBean = HttpResultBean<T>()
        try {
            //执行异步网络请求
            val response = okHttpClient.newCall(request).execute()
            //获取HTTP状态码
            httpResultBean.httpCode = response.code()
            //获取Response回执信息
            httpResultBean.msg = response.message()
            //请求成功进行解析
            if (response.isSuccessful) {
                if (clazz.simpleName == String::class.java.simpleName) {
                    httpResultBean.bean = response.body()?.string() as T
                } else {
                    httpResultBean.bean = iResponseParser.parserResponse(response.body()?.string(), clazz)
                }
            }
        } catch (e: Exception) {
            //出现异常获取异常信息
            httpResultBean.exception = e
            e.printStackTrace()
        }
        //返回请求结果
        return httpResultBean
    }
}