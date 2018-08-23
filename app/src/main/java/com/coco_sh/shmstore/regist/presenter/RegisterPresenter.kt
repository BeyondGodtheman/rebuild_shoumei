package com.coco_sh.shmstore.regist.presenter

import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.SmApplication
import com.coco_sh.shmstore.base.BaseModel
import com.coco_sh.shmstore.base.BasePresenter
import com.coco_sh.shmstore.http.ApiManager
import com.coco_sh.shmstore.login.manager.UserManager
import com.coco_sh.shmstore.login.model.Login
import com.coco_sh.shmstore.regist.data.RegisterLoader
import com.coco_sh.shmstore.regist.view.IRegisterView
import com.coco_sh.shmstore.sms.data.SMSLoader
import com.coco_sh.shmstore.sms.model.SmSMS
import com.coco_sh.shmstore.sms.type.SmsType
import com.coco_sh.shmstore.utils.ToastUtil

/**
 * 注册View
 * Created by zhangye on 2018/8/2.
 */
class RegisterPresenter(private var registerView: IRegisterView?) : BasePresenter<IRegisterView>(registerView) {

    private var smsLoader: SMSLoader? = null //短信数据请求类
    private var registerLoader: RegisterLoader? = null //注册请求类

    private var phoneOk = false  //电话号码验证条件
    private var codeOk = false  //短信验证码验证条件
    private var isArgeen = false //注册协议验证条件
    private var passwordOk = false //密码验证条件
    private var surePasswordOk = false //确认密码验证条件
    private var smsKey = "" //短信验证的key

    override fun onCreate() {
        smsLoader = SMSLoader(composites)
        registerLoader = RegisterLoader(composites)
    }


    //发送注册验证码
    fun sendSMS(phone: String, smsType: SmsType) {
        registerView?.showLoading()
        smsLoader?.sendCode(phone, smsType, object : ApiManager.OnResult<BaseModel<SmSMS>>() {
            override fun onSuccess(data: BaseModel<SmSMS>) {
                registerView?.hidenLoading()
                smsKey = data.message?.smskey ?: ""
                registerView?.snedSMSResult(data)
            }

            override fun onFailed(code: String, message: String) {
                registerView?.hidenLoading()
            }
        })

    }

    //注册账号
    fun regist(phone: String, code: String, password: String, surePassword: String) {
        if (!isArgeen) {
            ToastUtil.show("须同意此协议后再进行注册")
            return
        }

        if (password != surePassword) {
            ToastUtil.show(SmApplication.getApp().getString(R.string.inputPwdError))
            return
        }

        registerView?.showLoading()
        registerLoader?.regist(phone, code, password, smsKey, object : ApiManager.OnResult<BaseModel<Login>>() {
            override fun onSuccess(data: BaseModel<Login>) {
                registerView?.hidenLoading()
                data.message?.let {
                    UserManager.setLogin(it)
                    registerView?.registResult()
                }
            }

            override fun onFailed(code: String, message: String) {
                registerView?.hidenLoading()
            }

        })
    }

    /**
     * 检查手机号码长度
     */
    fun checkPhone(length: Int) {
        phoneOk = length == 11
        registerView?.codeBtnSata(phoneOk)
        isOk()
    }

    /**
     * 检查手机号码长度
     */
    fun checkCode(length: Int) {
        codeOk = length == 6
        isOk()
    }

    /**
     * 检查密码长度
     */
    fun checkPassWrod(length: Int, isSure: Boolean) {
        if (!isSure) {
            passwordOk = length >= 6
        } else {
            surePasswordOk = length >= 6
        }
        isOk()
    }

    /**
     * 检查协议状态
     */
    fun checkArgeen(isChecked: Boolean) {
        isArgeen = isChecked
        isOk()
    }


    /**
     * 校验提交条件
     */
    private fun isOk() {
        if (phoneOk && codeOk && passwordOk && surePasswordOk) {
            registerView?.registBtnSata(true)
        } else {
            registerView?.registBtnSata(false)
        }
    }


    override fun onDistroy() {
        registerLoader = null
        registerView = null
    }
}