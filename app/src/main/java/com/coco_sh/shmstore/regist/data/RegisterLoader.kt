package com.coco_sh.shmstore.regist.data

import com.coco_sh.shmstore.SmApplication
import com.coco_sh.shmstore.base.BaseModel
import com.coco_sh.shmstore.http.ApiManager
import com.coco_sh.shmstore.http.Constant
import com.coco_sh.shmstore.login.model.Login
import com.coco_sh.shmstore.utils.DigestUtils
import io.reactivex.disposables.CompositeDisposable

/**
 * 注册请求Loader
 * Created by zhangye on 2018/8/2.
 */
class RegisterLoader(private var composites: CompositeDisposable) {

    fun regist(phone: String, smscode: String, passwd: String, smskey: String, onResult: ApiManager.OnResult<BaseModel<Login>>) {
        val map = HashMap<String, String>()
        map["phone"] = phone
        map["passwd"] = DigestUtils.md5(passwd)
        map["smscode"] = smscode
        map["smskey"] = smskey
        map["client"] = Constant.CLIENT
        map["device"] = SmApplication.getApp().getDeviceID()
        ApiManager.post(composites, map, Constant.REGISTER, onResult)
    }
}