package com.coco_sh.shmstore.regist.data

import android.annotation.SuppressLint
import com.coco_sh.shmstore.SmApplication
import com.coco_sh.shmstore.base.BaseModel
import com.coco_sh.shmstore.base.IBaseView
import com.coco_sh.shmstore.http.ApiManager
import com.coco_sh.shmstore.http.Constant
import com.coco_sh.shmstore.login.model.Login
import io.reactivex.disposables.CompositeDisposable

/**
 * 注册请求Loader
 * Created by zhangye on 2018/8/2.
 */
class RegisterLoader(private var composites: CompositeDisposable) {

    @SuppressLint("HardwareIds")
    fun regist(phone: String, passwd: String, smscode: String, smskey: String, onResult: ApiManager.OnResult<BaseModel<Login>>) {
        val map = HashMap<String, String>()
        map["phone"] = phone
        map["passwd"] = passwd
        map["smscode"] = smscode
        map["smskey"] = smskey
        map["client"] = Constant.CLIENT
        map["device"] = SmApplication.getApp().getDeviceID()
        ApiManager.post(composites, map, Constant.REGISTER, onResult)
    }
}