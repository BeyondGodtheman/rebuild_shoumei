package com.sunnyfor.zy.http.bean

import com.sunnyfor.zy.utils.ToastUtil

/**
 * Desc
 * Author JoannChen
 * Mail yongzuo.chen@foxmail.com
 * Date 2020/4/29 12:37
 */
class HttpResultBean<T> {
    var httpCode = 0 //请求code
    var msg: String? = ""  //请求结果
    var exception: Exception? = null //网络请求异常信息
    var bean: T? = null //数据结果


    fun isSuccess(): Boolean {
        if (exception == null) {
            if (httpCode in 200..299) {
                return true
            }
        } else {
            if (msg?.isEmpty() == true) {
                ToastUtil.show(exception?.message)
            } else {
                ToastUtil.show(msg)
            }
        }
        return false
    }

    override fun toString(): String {
        return "HttpResultBean(httpCode=$httpCode, msg='$msg', exception=$exception, bean=$bean)"
    }


}