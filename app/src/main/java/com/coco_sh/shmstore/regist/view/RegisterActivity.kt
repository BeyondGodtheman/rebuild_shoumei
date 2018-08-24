package com.coco_sh.shmstore.regist.view

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.CompoundButton
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.base.BaseActivity
import com.coco_sh.shmstore.base.BaseModel
import com.coco_sh.shmstore.regist.presenter.RegisterPresenter
import com.coco_sh.shmstore.sms.model.SmSMS
import com.coco_sh.shmstore.sms.type.SmsType
import com.coco_sh.shmstore.utils.LoadingUtil
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_register.*

/**
 * 注册页面
 * Created by zhangye on 2018/8/2.
 */
class RegisterActivity : BaseActivity(), IRegisterView {

    private val registerPresenter: RegisterPresenter by lazy {
        RegisterPresenter(this)
    }

    private val loadingUtil: LoadingUtil by lazy {
        LoadingUtil(frameBody)
    }

    override fun showLoading() {
        loadingUtil.showLoading()
    }

    override fun hidenLoading() {
        loadingUtil.hidenLoading()
    }


    override fun setLayout(): Int = R.layout.activity_register


    override fun initView() {

        registerPresenter.onCreate()  //初始化Presentern逻辑

        frameTitle.addView(titleManager.defaultTitle("注册"))
        btnCode.setCallListener(View.OnClickListener {
            registerPresenter.sendSMS(edtPhone.text.toString(), SmsType.REGISTER)
        })


        //手机号码长度监听
        edtPhone.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                registerPresenter.checkPhone(s?.length ?: 0)
            }

        })

        //短信验证码长度监听
        edtCode.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                registerPresenter.checkCode(s?.length ?: 0)
            }
        })

        //密码长度监听
        edtPassWord.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                registerPresenter.checkPassWrod(s?.length ?: 0, false)
            }
        })

        //确认密码
        editSurePassWord.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                registerPresenter.checkPassWrod(s?.length ?: 0, true)
            }
        })

        //协议按钮监听
        tvChecked.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, isChecked ->
            registerPresenter.checkArgeen(isChecked)
        })


        btnNext.setOnClickListener(this)
    }

    override fun update() {
    }

    override fun loadData() {

    }


    override fun onClick(view: View) {
        when (view.id) {   //开始注册
            btnNext.id -> registerPresenter.regist(edtPhone.text.toString(), edtCode.text.toString(),
                    edtPassWord.text.toString(), editSurePassWord.text.toString())
        }
    }


    //注册按钮状态
    override fun registBtnSata(boolean: Boolean) {
        btnNext.isClickable = boolean

        if (boolean) {
            btnNext.setBackgroundResource(R.color.red)
        } else {
            btnNext.setBackgroundResource(R.color.grayBtn)
        }
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