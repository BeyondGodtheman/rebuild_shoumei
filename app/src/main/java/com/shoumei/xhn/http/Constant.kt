package com.shoumei.xhn.http

/**
 * 接口配置清单
 * Created by zhangye on 2017/10/12.
 */
object Constant {

    fun isDebug(): Boolean = true

    var isLog = true //是否打印LOG

    const val APP_SECRET = "SM_ANDROID" //接口SECRET
    const val VERSION = "v1"  //接口版本
    const val CLIENT = "android"

    const val SMEDIA_PHONE = "01078334322" //首媒客服电话


    const val COMMON_DATA = "$VERSION/common/data" //通用接口（认证状态）

    const val REGISTER_SEND_CODE = "$VERSION/common/sms" //注册发送验证码

    const val COMMON_UPLOADS = "$VERSION/common/uploads" //上传文件

    const val REGISTER = "$VERSION/portal/register" //注册

    const val LOGIN = "$VERSION/portal/login"//登录

    const val FORGOTPWD = "$VERSION/portal/forgotpwd" //忘记密码

    const val PROFILE = "$VERSION/profile" //档案接口

    const val PROFILE_UPDATE = "$VERSION/profile/update" //更新档案
}

