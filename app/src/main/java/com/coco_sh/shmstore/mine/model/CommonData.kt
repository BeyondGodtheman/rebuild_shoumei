package com.coco_sh.shmstore.mine.model

/**
 * 通用信息实体类
 * Created by zhangye on 2018/8/28.
 */
data class CommonData(
        var smno: String?, // 首媒号
        var degree: Int, // 用户信息档案信息完善度
        var avatar: String?, // 头像
        var phone: String?,  // 手机号
        var nickname: String?, // 昵称
        var realname: String?, // 真实姓名
        var paypass: Int, // 是否已初始化支付密码
        var cert: Cert?) {
    data class Cert(
            var r: Int, // 实名认证状态: '0'-未认证,'1'-已认证
            var x: Int, // 新媒人认证状态: '0'-未认证,'1'-待付款,'2'-已认证
            var f: Int, // 服务商认证状态: '0'-未认证,'1'-认证中(通常线下付款),'2'-认证失败,'3'-已认证
            var b: Int) // 企业主认证状态: '0'-未认证,'1'-(已认证)待激活,'2'-(提交激活信息)审核中,'3'-(审核未通过)激活失败,'4'-(审核已通过)已认证且已激活
}