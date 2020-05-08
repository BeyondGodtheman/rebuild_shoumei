package com.shoumei.xhn.http

import okhttp3.Interceptor
import okhttp3.Response

/**
 * 头信息
 * Created by ZhangYe on 2018/2/7.
 */
class HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val authorised = originalRequest.newBuilder()
                .header("authorization", getAuthorization())
                .build()
        return chain.proceed(authorised)
    }

    /**
     * 生成服务验证头信息
     */
    private fun getAuthorization(): String {
        val authorizationStr = StringBuilder("sm-auth-${Constant.VERSION}")
        return authorizationStr.toString()
    }
}