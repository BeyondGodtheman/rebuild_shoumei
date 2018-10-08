package com.shoumei.xhn.login.data

import com.shoumei.xhn.SmApplication
import com.shoumei.xhn.base.BaseModel
import com.shoumei.xhn.http.ApiManager
import com.shoumei.xhn.http.Constant
import com.shoumei.xhn.login.model.Login
import com.shoumei.xhn.utils.DigestUtils
import com.shoumei.xhn.utils.StringUtils
import io.reactivex.disposables.CompositeDisposable

/**
 * 登录
 * Created by zhangye on 2018/8/2.
 */
class LoginLoader(private var composites: CompositeDisposable) {

    //用户名密码登录
    fun login(phone: String, password: String,onResult:ApiManager.OnResult<BaseModel<Login>>){
            val map = HashMap<String, String>()
            map["ts"] = StringUtils.getTimeStamp()
            map["phone"] = phone
            map["passwd"] = DigestUtils.sha1(DigestUtils.md5(password) + StringUtils.getTimeStamp())
            map["client"] = Constant.CLIENT
            map["device"] =  SmApplication.getApp().getDeviceID()
            ApiManager.post(composites, map, Constant.LOGIN,onResult)
    }
}