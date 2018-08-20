package com.coco_sh.shmstore.login.presenter

import android.text.Editable
import android.text.TextWatcher
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.base.BaseModel
import com.coco_sh.shmstore.base.BasePresenter
import com.coco_sh.shmstore.base.IBaseView
import com.coco_sh.shmstore.http.ApiManager
import com.coco_sh.shmstore.login.data.LoginLoader
import com.coco_sh.shmstore.login.model.Login
import com.coco_sh.shmstore.login.model.LoginHistory
import com.coco_sh.shmstore.login.view.ILoginView

/**
 * 登录
 * Created by zhangye on 2018/8/2.
 */
class LoginPresenter(private var iLoginView: ILoginView?) : BasePresenter<IBaseView>(iLoginView) {
    private var phoneOK = false
    private var passOK = false
    private var loginLoader: LoginLoader<ILoginView>? = null


    override fun onCreate() {
        iLoginView?.let {
            loginLoader = LoginLoader(it)

            //监听帐号输入达到位数通过条件
            it.getEditPhone().addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.let {
                        phoneOK = it.length >= 11
                        isOk()
                    }
                }
            })

            //监听密码输入达到位数通过条件
            it.getEditPass().addTextChangedListener(object : TextWatcher {


                override fun afterTextChanged(s: Editable?) {
                }

                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                    s?.let {
                        passOK = it.length >= 6
                        isOk()
                    }
                }
            })

        }

    }

    //用户名密码登录
    fun login() {
        val phone = iLoginView?.getEditPhone()?.text.toString()
        val password = iLoginView?.getEditPass()?.text.toString()
        loginLoader?.login(phone, password, object : ApiManager.OnResult<BaseModel<Login>>() {

            override fun onSuccess(data: BaseModel<Login>) {
                val loginHistory = LoginHistory(phone)
                addHistory(loginHistory)  //添加登录历史记录
                iLoginView?.loginResult(data, false) //回调登录结果
            }

            override fun onFailed(code: String, message: String) {}
        })
    }


    //加载登录历史记录
    fun getHistorys(): List<LoginHistory>? = loginLoader?.getHistorys()


    //添加登录历史记录
    fun addHistory(history: LoginHistory) {
        loginLoader?.addHistory(history)
    }

    //删除登录历史记录
    fun removeHistory(history: LoginHistory) {
        loginLoader?.removeHistory(history)
    }


    //输入条件检查
    fun isOk() {
        iLoginView?.getBtnLogin()?.apply {
            if (phoneOK && passOK) {
                isClickable = true
                setBackgroundResource(R.color.red)
            } else {
                isClickable = false
                setBackgroundResource(R.color.grayBtn)
            }
        }

    }


    override fun onDistroy() {
        iLoginView = null
        loginLoader = null
    }

}