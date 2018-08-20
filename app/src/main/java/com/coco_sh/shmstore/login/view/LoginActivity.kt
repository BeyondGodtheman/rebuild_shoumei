package com.coco_sh.shmstore.login.view

import android.content.Intent
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.base.BaseActivity
import com.coco_sh.shmstore.base.BaseModel
import com.coco_sh.shmstore.forget.view.ForgetPassActivity
import com.coco_sh.shmstore.login.manager.UserManager
import com.coco_sh.shmstore.login.model.Login
import com.coco_sh.shmstore.login.model.LoginHistory
import com.coco_sh.shmstore.login.presenter.LoginPresenter
import com.coco_sh.shmstore.regist.view.RegisterActivity
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_login.*

/**
 * 登录页面
 * Created by zhangye on 2018/8/2.
 */
class LoginActivity : BaseActivity(), ILoginView {
    var loginPresenter = LoginPresenter(this)

    override fun showLoading() {

    }

    override fun hidenLoading() {

    }

    //登录结果回调
    override fun loginResult(result: BaseModel<Login>, isOtherLogin: Boolean) {
    }

    override fun onHistory(history: List<LoginHistory>) {
    }

    override fun setLayout(): Int = R.layout.activity_login

    override fun initView() {
        frameTitle.addView(titleManager.loginTitle())
        UserManager.setEmptyUser() //清空本地用户信息
        loginPresenter.getHistorys()
        btnLogin.setOnClickListener(this)
        llReg.setOnClickListener(this)
        tvForgetPsd.setOnClickListener(this)

    }

    override fun update() {
    }

    override fun loadData() {
    }


    override fun onClick(view: View) {
        when(view.id){
            btnLogin.id ->loginPresenter.login() //登录请求
            llReg.id -> startActivity(Intent(this, RegisterActivity::class.java)) //跳转注册
            tvForgetPsd.id -> startActivity(Intent(this, ForgetPassActivity::class.java))
        }
    }


    override fun gonePhone(history: LoginHistory) {

    }


    override fun getEditPhone(): EditText = edtPhone

    override fun getEditPass(): EditText  = edtPassword

    override fun getBtnLogin(): Button = btnLogin

    override fun close() {

    }
}