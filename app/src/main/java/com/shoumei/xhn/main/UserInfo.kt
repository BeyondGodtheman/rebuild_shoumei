package com.shoumei.xhn.main

/**
 * Desc
 * Author JoannChen
 * Mail yongzuo.chen@foxmail.com
 * Date 2020/4/29 15:15
 */
data class UserInfo(
        val code: Int,
        val data: Data,
        val msg: String
)

data class Data(
        val address: Any,
        val avatar: Int,
        val companyLocation: Any,
        val companyName: Any,
        val companyShortName: Any,
        val companyType: Any,
        val createTime: Long,
        val creditCode: Any,
        val deptId: Int,
        val deptName: Any,
        val email: Any,
        val idCard: String,
        val imagesId: Int,
        val invatationCode: String,
        val isDisables: Int,
        val levelCode: String,
        val levelName: Any,
        val levelNickName: Any,
        val mobile: Any,
        val nickName: String,
        val postCode: Any,
        val postName: Any,
        val qyuserId: Any,
        val rankCode: Any,
        val rankName: Any,
        val refereeId: Any,
        val refereeName: Any,
        val roleIdList: List<Int>,
        val salt: String,
        val sex: Any,
        val shiFouGly: Int,
        val sign: Any,
        val status: Any,
        val token: String,
        val userId: Int,
        val username: String,
        val verificationCode: Any
)