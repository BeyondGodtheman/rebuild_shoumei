package com.coco_sh.shmstore.login.data

import com.coco_sh.shmstore.SmApplication
import com.coco_sh.shmstore.base.BaseModel
import com.coco_sh.shmstore.base.IBaseView
import com.coco_sh.shmstore.http.ApiManager
import com.coco_sh.shmstore.http.Constant
import com.coco_sh.shmstore.login.model.Login
import com.coco_sh.shmstore.login.model.LoginHistory
import com.coco_sh.shmstore.utils.DigestUtils
import com.coco_sh.shmstore.utils.StringUtils
import io.reactivex.disposables.CompositeDisposable
import xiaofei.library.comparatorgenerator.ComparatorGenerator

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