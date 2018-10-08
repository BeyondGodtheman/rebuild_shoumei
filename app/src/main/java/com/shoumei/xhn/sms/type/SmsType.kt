package com.shoumei.xhn.sms.type

/**
 * 短信类型枚举
 * Created by zhangye on 2018/8/2.
 */
enum class SmsType(var value: String) {
    REGISTER("register"), //注册
    RESET_PASS("reset_pass"), //忘记密码
    INVITE("invite"), //分享接受邀约
    RESET_PAYPASS("reset_paypass"),//重置支付密码
    ENT_CASH("ent_cash")//企业主提现
}