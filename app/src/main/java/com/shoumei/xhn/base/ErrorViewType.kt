package com.shoumei.xhn.base

/**
 * 错误View的类型枚举
 * Created by zhangye on 2018/8/2.
 */
enum class ErrorViewType(var value:String) {
    NETWORK("network"),//网络错误
    TIMEOUT("timeout"),//请求超时
    NODATA("nodata"),//没有数据
}