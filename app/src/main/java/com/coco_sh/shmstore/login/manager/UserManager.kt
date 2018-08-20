package com.coco_sh.shmstore.login.manager

import android.graphics.Bitmap
import com.coco_sh.shmstore.login.model.Login
import com.coco_sh.shmstore.utils.SharedUtil
import com.google.gson.Gson


/**
 * 用户实体类
 * Created by asus on 2017/1/4.
 */

object UserManager {
    //存储用户信息
    private const val LOGIN = "login"
    //我的页面入口数据
    private const val MEMBERENTRANCE = "memberentrance"

    private var bitmap_topbg: Bitmap? = null
    private var default_bg: Bitmap? = null

    private var tag: String? = null
    /**
     * 获取用户ID
     */
    fun getUserId(): String =  getLogin()?.code?:""


    /**
     * 获取用户Token
     */
    fun getUserToken(): String = getLogin()?.access_token ?: ""


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
     * 将所有本地数据置空
     */
    fun setEmptyUser() {
        SharedUtil.remove(LOGIN)
        SharedUtil.remove(MEMBERENTRANCE)
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
