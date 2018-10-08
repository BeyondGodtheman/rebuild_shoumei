package com.shoumei.xhn.login.manager

import com.shoumei.xhn.login.model.Login
import com.shoumei.xhn.mine.model.CommonData
import com.shoumei.xhn.mine.model.Profile
import com.shoumei.xhn.utils.SharedUtil
import com.google.gson.Gson


/**
 * 用户实体类
 * Created by asus on 2017/1/4.
 */

object UserManager {
    //存储用户信息
    private const val LOGIN = "login"
    //档案资料
    private const val PROFILE = "profile"
    //通用数据
    private const val COMMON = "common"

    /**
     * 获取用户ID
     */
    fun getUserId(): String = getLogin()?.code ?: ""


    /**
     * 获取用户Token
     */
    fun getUserToken(): String = getLogin()?.access_token ?: ""


    /**
     * 获取用户SID
     */
    fun getUserSid(): String = getLogin()?.sid ?: ""


    /**
     * 存储登录信息
     */
    fun setLogin(login: Login) {
        SharedUtil.setString(LOGIN, Gson().toJson(login))
    }

    /**
     * 获取存储登录信息
     */
    fun getLogin(): Login? {
        val json = SharedUtil.getString(LOGIN)
        if (json != "") {
            return Gson().fromJson(json, Login::class.java)
        }
        return null
    }

    /**
     * 存储通用信息
     */
    fun setCommon(profile: CommonData) {
        SharedUtil.setString(COMMON, Gson().toJson(profile))
    }


    /**
     * 获取通用信息
     */
    fun getCommon(): CommonData? {
        val json = SharedUtil.getString(COMMON)
        if (json != "") {
            return Gson().fromJson(json, CommonData::class.java)
        }
        return null
    }


    fun getCryptogramPhone(): String {
        val sb = StringBuilder()
        getCommon()?.phone?.let {
            sb.append(it.substring(0, 3))
            sb.append("****")
            sb.append(it.substring(7, 11))
        }
        return sb.toString()
    }


    /**
     * 存储档案信息
     */
    fun setProfile(profile: Profile) {
        SharedUtil.setString(PROFILE, Gson().toJson(profile))
    }


    /**
     * 获取档案存储信息
     */
    fun getProfile(): Profile? {
        val json = SharedUtil.getString(PROFILE)
        if (json != "") {
            return Gson().fromJson(json, Profile::class.java)
        }
        return null
    }


    /**
     * 将所有本地数据置空
     */
    fun setEmptyUser() {
        SharedUtil.remove(LOGIN)
        SharedUtil.remove(PROFILE)
        SharedUtil.remove(COMMON)
    }

    /**
     * 判断是否登录
     */
    fun isLogin(): Boolean {
        if (getUserId() != "") {
            return true
        }
        return false
    }

}
