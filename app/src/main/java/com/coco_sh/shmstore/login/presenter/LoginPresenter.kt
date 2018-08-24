package com.coco_sh.shmstore.login.presenter

import com.coco_sh.shmstore.SmApplication
import com.coco_sh.shmstore.base.BaseModel
import com.coco_sh.shmstore.base.BasePresenter
import com.coco_sh.shmstore.base.IBaseView
import com.coco_sh.shmstore.http.ApiManager
import com.coco_sh.shmstore.login.data.LoginLoader
import com.coco_sh.shmstore.login.model.Login
import com.coco_sh.shmstore.login.model.LoginHistory
import com.coco_sh.shmstore.login.view.ILoginView
import xiaofei.library.comparatorgenerator.ComparatorGenerator

/**
 * 登录
 * Created by zhangye on 2018/8/2.
 */
class LoginPresenter(private var iLoginView: ILoginView?) : BasePresenter<IBaseView>(iLoginView) {
    private var phoneOK = false
    private var passOK = false
    private var loginLoader: LoginLoader? = null


    override fun onCreate() {
        loginLoader = LoginLoader(composites)
    }


    //用户名密码登录
    fun login(phone: String, password: String) {
        loginLoader?.login(phone, password, object : ApiManager.OnResult<BaseModel<Login>>() {

            override fun onSuccess(data: BaseModel<Login>) {
                val loginHistory = LoginHistory(phone)
                SmApplication.getApp().dataStorage.storeOrUpdate(loginHistory) //添加登录历史记录
                iLoginView?.loginResult(data, false) //回调登录结果
            }

            override fun onFailed(code: String, message: String) {}
        })
    }

    //检查手机号码长度
    fun checkPhone(length: Int) {
        phoneOK = length >= 11
        isOk()
    }


    //检查密码长度
    fun checkPassword(length: Int) {
        passOK = length >= 6
        isOk()
    }


    //加载登录历史记录
    fun getHistorys() {
        val comparator = ComparatorGenerator<LoginHistory>(LoginHistory::class.java).generate() //按时间排序
        iLoginView?.onHistory(SmApplication.getApp().dataStorage.loadAll(LoginHistory::class.java, comparator) as ArrayList<LoginHistory>)
    }


    //输入条件检查
    private fun isOk() {
        iLoginView?.apply {
            if (phoneOK && passOK) {
                loginBtnState(true)
            } else {
                loginBtnState(false)
            }

        }
    }


    override fun onDistroy() {
        iLoginView = null
        loginLoader = null
    }

}