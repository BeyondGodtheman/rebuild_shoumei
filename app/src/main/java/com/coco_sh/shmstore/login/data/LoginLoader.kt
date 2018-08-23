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
import xiaofei.library.comparatorgenerator.ComparatorGenerator

/**
 * 登录
 * Created by zhangye on 2018/8/2.
 */
class LoginLoader<T : IBaseView>(private var iBaseView: T?) {

    //用户名密码登录
    fun login(phone: String, password: String,onResult:ApiManager.OnResult<BaseModel<Login>>){
        iBaseView?.let {
            val map = HashMap<String, String>()
            map["ts"] = StringUtils.getTimeStamp()
            map["phone"] = phone
            map["passwd"] = DigestUtils.sha1(DigestUtils.md5(password) + StringUtils.getTimeStamp())
            map["client"] = Constant.CLIENT
            map["device"] =  SmApplication.getApp().getDeviceID()
//            ApiManager.post(it, map, Constant.LOGIN,onResult)
        }
    }



    /**
     * 获取历史数据
     */
    fun getHistorys(): List<LoginHistory> {
        val comparator = ComparatorGenerator<LoginHistory>(LoginHistory::class.java).generate()
        return SmApplication.getApp().dataStorage.loadAll(LoginHistory::class.java, comparator)
    }


    /**
     * 添加历史记录
     */
    fun addHistory(history: LoginHistory) {
        SmApplication.getApp().dataStorage.storeOrUpdate(history)
    }

    /**
     * 删除历史记录
     */
    fun removeHistory(history: LoginHistory) {
        SmApplication.getApp().dataStorage.delete(history)
    }
}