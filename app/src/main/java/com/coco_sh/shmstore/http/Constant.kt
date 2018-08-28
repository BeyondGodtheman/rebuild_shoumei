package com.coco_sh.shmstore.http

import com.coco_sh.shmstore.utils.DataKey
import com.coco_sh.shmstore.utils.SharedUtil

/**
 * 接口配置清单
 * Created by zhangye on 2017/10/12.
 */
object Constant {

    fun isDebug(): Boolean = !SharedUtil.getBoolean(DataKey.ISDEBUG, true)

    var SHOWLOG = true //是否打印LOG

    const val APPSECRET = "SM_ANDROID" //接口SECRET
    const val VERSION = "v1"  //接口版本
    const val CLIENT = "android"

    const val SMEDIA_PHONE = "01078334322" //首媒客服电话


    const val COMMON_DATA = "$VERSION/common/data" //通用接口（认证状态）

    const val REGISTER_SEND_CODE = "$VERSION/common/sms" //注册发送验证码

    const val COMMON_UPLOADS = "$VERSION/common/uploads" //上传文件

    const val REGISTER = "$VERSION/portal/register" //注册

    const val LOGIN = "$VERSION/portal/login"//登录

    const val FORGOTPWD = "$VERSION/portal/forgotpwd" //忘记密码
}

