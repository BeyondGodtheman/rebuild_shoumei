package com.sunny.zy.http

import com.sunny.zy.ZyFrameStore

/**
 * Desc
 * Author 张野
 * Mail zhangye98@foxmail.com
 * Date 2020/6/2 16:47
 */
object UrlConstant {

    private const val IP = "10.0.0.153" // 内网测试地址
//    const val ip = "www.zhenhekj.com" //正式地址


    private const val PORT = "8807"

    const val HOST = "https://$IP:$PORT"


    const val IMAGE_URL = "/sys/sysfujian/download?attID="


    val TEMP = ZyFrameStore.getContext().getExternalFilesDir("temp")?.path //内存卡缓存路径

    const val SYS_LOGIN = "sys/login" //登录接口

    /**
     * 检查版本更新
     * versionNumber=1.0.0.1
     */
    const val APP_VERSION_UPDATE_URL = "app/appandroidversion/findNewOne"


    /**
     * 字典接口
     */



    /**
     * 文件上传
     */
    const val STS_UPLOAD = "sys/sysfujian/upload"

}