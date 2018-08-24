package com.coco_sh.shmstore.login.view

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.base.BaseActivity
import com.coco_sh.shmstore.base.BaseModel
import com.coco_sh.shmstore.click.OnItemClickListener
import com.coco_sh.shmstore.forget.view.ForgetPassActivity
import com.coco_sh.shmstore.login.manager.UserManager
import com.coco_sh.shmstore.login.model.Login
import com.coco_sh.shmstore.login.model.LoginHistory
import com.coco_sh.shmstore.login.presenter.LoginPresenter
import com.coco_sh.shmstore.regist.view.RegisterActivity
import com.coco_sh.shmstore.utils.LoadingUtil
import com.coco_sh.shmstore.widget.popupwindow.HistoryPhonePopupWindow
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

/**
 * 登录页面
 * Created by zhangye on 2018/8/2.
 */
class LoginActivity : BaseActivity(), ILoginView {
    private var isShow = false
    private var loginPresenter = LoginPresenter(this)
    private val loadingUtil: LoadingUtil by lazy {
        LoadingUtil(frameBody)
    }

    override fun showLoading() {
        loadingUtil.showLoading()
    }

    override fun hidenLoading() {
        loadingUtil.hidenLoading()
    }


    override fun setLayout(): Int = R.layout.activity_login

    override fun initView() {
        frameTitle.addView(titleManager.loginTitle())
        UserManager.setEmptyUser() //清空本地用户信息
        loginPresenter.onCreate()
        btnLogin.setOnClickListener(this)
        llReg.setOnClickListener(this)
        tvForgetPsd.setOnClickListener(this)
        itvDown.setOnClickListener(this)

        //监听手机号码输入位数判断
        edtPhone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                loginPresenter.checkPhone(s?.length ?: 0)
            }

        })

        //监听密码输入位数判断
        edtPassword.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(s: CharSequence?, p1: Int, p2: Int, p3: Int) {
                loginPresenter.checkPassword(s?.length ?: 0)
            }

        })


    }

    override fun update() {
    }

    override fun loadData() {
        loginPresenter.getHistorys()
    }


    override fun onClick(view: View) {
        when (view.id) {
            btnLogin.id -> loginPresenter.login(edtPhone.text.toString(), edtPassword.text.toString()) //登录请求
            llReg.id -> startActivity(Intent(this, RegisterActivity::class.java)) //跳转注册
            tvForgetPsd.id -> startActivity(Intent(this, ForgetPassActivity::class.java)) //跳转忘记密码
            itvDown.id -> {
                isShow = true
                loginPresenter.getHistorys()
                hideKeyboard()
            }

        }
    }


    override fun loginBtnState(boolean: Boolean) {
        btnLogin.isClickable = boolean

        if (boolean) {
            btnLogin.setBackgroundResource(R.color.red)
        } else {
            btnLogin.setBackgroundResource(R.color.grayBtn)
        }

    }


    //登录结果回调
    override fun loginResult(result: BaseModel<Login>, isOtherLogin: Boolean) {
        finish()
    }

    //显示登录历史
    override fun onHistory(history: ArrayList<LoginHistory>) {

        if (isShow) {
            val historyPhonePopupWindow = HistoryPhonePopupWindow(this, history, edtPhone)
            historyPhonePopupWindow.onDeleteLisenter = object : HistoryPhonePopupWindow.OnDeleteListener {
                override fun onDeleteResult(loginHistory: LoginHistory) {
                    if (edtPhone.text.toString() == loginHistory.phone) {
                        if (history.isNotEmpty()) {
                            edtPhone.setText(history.first().phone)
                            edtPhone.setSelection(edtPhone.text.length) //设置光标位置
                        } else {
                            edtPhone.setText("")
                        }
                    }
                }
            }


            historyPhonePopupWindow.setOnItemClickListener(object : OnItemClickListener {
                override fun onItemClick(v: View, index: Int) {
                    edtPhone.setText(history[index].phone)
                    edtPhone.setSelection(edtPhone.text.length) //设置光标位置
                    historyPhonePopupWindow.dismiss()
                }

            })


            launch(UI) {
                delay(100)
                historyPhonePopupWindow.showAsDropDown(edtPhone)
            }

        } else {
            if (history.isNotEmpty()) {
                itvDown.visibility = View.VISIBLE
                edtPhone.setText(history.first().phone)
                edtPhone.setSelection(edtPhone.text.length) //设置光标位置
                loginPresenter.checkPhone(history.first().phone.length)
            }
        }
    }


    override fun close() {

    }
}