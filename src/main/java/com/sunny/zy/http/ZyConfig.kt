package com.sunny.zy.http

import android.os.Environment
import com.sunny.zy.ZyFrameStore
import com.sunny.zy.http.interceptor.HeaderInterceptor

/**
 * 接口配置清单
 * Created by zhangye on 2017/10/12.
 */
object ZyConfig {

    fun isDebug(): Boolean = true

    /**
     * 是否打印LOG
     */
    var isLog = true

    /**
     * IP地址
     */
    var IP = "127.0.0.1" // 内网测试地址

    /**
     * 端口
     */
    var PORT = "80"

    /**
     * 域名前缀：http? https
     */
    var HOST_PREFIX = "https"

    /**
     * 域名变量
     */
    val HOST = "$HOST_PREFIX://$IP:$PORT"

    /**
     * provider权限
     */
    var authorities = "com.sunny.zy.provider"

    val DOWNLOADS = ZyFrameStore.getContext().getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)?.path ?: "" //内存卡缓存路径


    val headerInterceptor: HeaderInterceptor by lazy {
        HeaderInterceptor()
    }

    /**
     * 设置头信息
     */
    fun setHttpHeader(headerMap: HashMap<String, Any>) {
        headerInterceptor.setHttpHeader(headerMap)
    }
}

