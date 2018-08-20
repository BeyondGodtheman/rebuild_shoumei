package com.coco_sh.shmstore.http

import com.coco_sh.shmstore.utils.DigestUtils
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

    private fun getAuthorization(): String {
        val paramsArray = TreeMap<String, String>()
        paramsArray["auth_session"] = "95270"
        paramsArray["app_id"] = "android_id"
//        paramsArray["timestamp"] = (System.currentTimeMillis() / 1000).toString()
        paramsArray["timestamp"] = "1530491029"
//        paramsArray["noncestr"] = StringUtils.getNoncestr()
        paramsArray["noncestr"] = "JxRaZevm3"
//        paramsArray["token"] = UserManager.getUserToken()
        paramsArray["sid"] = "28"
        paramsArray["token"] = "wlliromhhx29dq8fyjrece4tihcwj5:jr0vvxZS3kDMRMeWSGe9olHqJ1s="


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
        authorizationStr.append(DigestUtils.md5(signStr.toString() + Constant.APPSECRET))
        return authorizationStr.toString()
    }
}