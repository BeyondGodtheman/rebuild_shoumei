package com.coco_sh.shmstore.regist.view

import android.view.View
import android.widget.EditText
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.base.BaseActivity
import com.coco_sh.shmstore.base.BaseModel
import com.coco_sh.shmstore.regist.presenter.RegisterPresenter
import com.coco_sh.shmstore.sms.model.SmSMS
import com.coco_sh.shmstore.sms.type.SmsType
import com.coco_sh.shmstore.widget.textView.CheckedIconTextView
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_register.*

/**
 * 注册页面
 * Created by zhangye on 2018/8/2.
 */
class RegisterActivity : BaseActivity(), IRegisterView {

    private val registerPresenter = RegisterPresenter(this)

    override fun showLoading() {
    }

    override fun hidenLoading() {
    }


    override fun setLayout(): Int = R.layout.activity_register


    override fun initView() {

        registerPresenter.onCreate()  //初始化Presentern逻辑

        frameTitle.addView(titleManager.defaultTitle("注册"))
        btnCode.setCallListener(View.OnClickListener {
            registerPresenter.sendSMS(edtPhone.text.toString(), SmsType.REGISTER)
        })


        btnNext.setOnClickListener(this)
    }

    override fun update() {
    }

    override fun loadData() {

    }

    override fun onClick(view: View) {
        when (view.id) {
            btnNext.id -> registerPresenter.regist()  //开始注册
        }
    }

    //手机号码
    override fun getEditPhoto(): EditText = edtPhone

    //验证码
    override fun getEditCode(): EditText = edtCode

    //密码
    override fun getEditPass(): EditText = edtPassWord

    //确认密码
    override fun getEditSurePass(): EditText = editSurePassWord

    //协议
    override fun getBtnIsArgeen(): CheckedIconTextView = tvChecked


    //注册按钮状态
    override fun registBtnSata(boolean: Boolean) {
        if (boolean) {
            btnNext.setBackgroundResource(R.color.red)
        } else {
            btnNext.setBackgroundResource(R.color.grayBtn)
        }
        btnNext.isClickable = boolean
    }

    //短信按钮状态
    override fun codeBtnSata(boolean: Boolean) {
        btnCode.setClick(boolean)
    }


    //发送验证码成功回调
    override fun snedSMSResult(sms: BaseModel<SmSMS>) {
        btnCode.action() //启动倒计时
    }

    //注册成功回调
    override fun registResult() {
        finish()
    }


    override fun close() {
        registerPresenter.onDistroy()
    }

}