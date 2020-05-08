package com.shoumei.xhn.http.parser

/**
 * Desc 数据解析
 * Author ZhangYe
 * Mail zhangye98@foxmail.com
 * Date 2020/4/29 14:47
 */
interface IResponseParser {
    fun <T> parserResponse(data: String?, clazz: Class<T>): T
}