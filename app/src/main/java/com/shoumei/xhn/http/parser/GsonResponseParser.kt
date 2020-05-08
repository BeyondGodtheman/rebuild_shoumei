package com.shoumei.xhn.http.parser

import com.google.gson.Gson

/**
 * Desc
 * Author ZhangYe
 * Mail zhangye98@foxmail.com
 * Date 2020/4/29 14:51
 */
class GSonResponseParser : IResponseParser {

    val mGSon = Gson()

    override fun <T> parserResponse(data: String?, clazz: Class<T>): T {
        return mGSon.fromJson(data, clazz)
    }
}