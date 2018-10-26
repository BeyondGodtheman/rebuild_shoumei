package com.shoumei.xhn.http

import com.shoumei.xhn.login.manager.UserManager
import com.shoumei.xhn.utils.DigestUtils
import com.shoumei.xhn.utils.StringUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*

/**
 * 头信息
 * Created by zhangye on 2018/2/7.
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
        if (UserManager.isLogin()) {
            val paramsArray = TreeMap<String, String>()
            paramsArray["auth_session"] = UserManager.getUserId()
            paramsArray["app_id"] = "android_id"
            paramsArray["timestamp"] = StringUtils.getTimeStamp()
            paramsArray["noncestr"] = StringUtils.getNoncestr()
            paramsArray["token"] = UserManager.getUserToken()
            paramsArray["sid"] = UserManager.getUserSid()


            val signStr = StringBuilder()
            paramsArray.forEach {
                signStr.append(it.key)
                signStr.append(it.value)
            }

            val authorizationStr = StringBuilder("sm-auth-${Constant.VERSION}").append("/")
            authorizationStr.append(paramsArray["auth_session"]).append("/")
            authorizationStr.append(paramsArray["app_id"]).append("/")
            authorizationStr.append(paramsArray["timestamp"]).append("/")
            authorizationStr.append(paramsArray["noncestr"]).append("/")
            authorizationStr.append(paramsArray["sid"]).append("/")
            authorizationStr.append(DigestUtils.md5(signStr.toString() + Constant.APP_SECRET))
            return authorizationStr.toString()
        }
        return ""
    }
}