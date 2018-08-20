package com.coco_sh.shmstore.forget.presenter

import android.support.v4.content.ContextCompat
import android.text.Editable
import android.text.TextWatcher
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.SmApplication
import com.coco_sh.shmstore.base.BaseModel
import com.coco_sh.shmstore.base.BasePresenter
import com.coco_sh.shmstore.base.IBaseView
import com.coco_sh.shmstore.forget.data.ForgetPassLoader
import com.coco_sh.shmstore.forget.view.IForgetPassView
import com.coco_sh.shmstore.http.ApiManager
import com.coco_sh.shmstore.sms.data.SMSLoader
import com.coco_sh.shmstore.sms.model.SmSMS
import com.coco_sh.shmstore.sms.type.SmsType

/**
 * 忘记密码的逻辑处理
 * Created by zhangye on 2018/8/3.
 */
class ForgetPassPresenter(private var iForgetPassView: IForgetPassView?) : BasePresenter<IBaseView>(iForgetPassView) {

    private var smskey = ""
    private var phoneOk = false
    private var codeOk = false
    private var passwdOk = false

    private var forgetPassLoader: ForgetPassLoader? = null
    private var smsLoader: SMSLoader? = null

    override fun onCreate() {
        forgetPassLoader = ForgetPassLoader(composites)
        smsLoader = SMSLoader(composites) //数据请求类

        iForgetPassView?.getEditCode()?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                iForgetPassView?.apply {
                    codeOk = charSequence?.length == 6
                    isOk()
                }


                codeOk = charSequence?.length == 6
            }
        })



        iForgetPassView?.getEditPhone()?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                phoneOk = if (charSequence?.length == 11) {
                    iForgetPassView?.getBtnSend()?.setClick(true)
                    true
                } else {
                    iForgetPassView?.getBtnSend()?.setClick(false)
                    false
                }

                isOk()
            }
        })

        iForgetPassView?.getEditPass()?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(charSequence: CharSequence?, p1: Int, p2: Int, p3: Int) {
                passwdOk = charSequence?.length ?: 0 > 6
                isOk()
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {


            }
        })


    }

    //忘记密码请求
    fun forgetPass() {
        forgetPassLoader?.forgetPass(
                iForgetPassView?.getEditPhone()?.text.toString(),
                iForgetPassView?.getEditPass()?.text.toString(),
                iForgetPassView?.getEditCode()?.text.toString(),
                smskey,
                object : ApiManager.OnResult<BaseModel<String>>() {
                    override fun onSuccess(data: BaseModel<String>) {
                        iForgetPassView?.forgetResult()
                    }

                    override fun onFailed(code: String, message: String) {}
                }
        )
    }

    //发送短信验证码
    fun sendMSM() {
        smsLoader?.sendCode(
                iForgetPassView?.getEditPhone()?.text.toString(),
                SmsType.RESET_PASS,
                object : ApiManager.OnResult<BaseModel<SmSMS>>() {
                    override fun onSuccess(data: BaseModel<SmSMS>) {
                        smskey = data.message?.smskey ?: ""
                        iForgetPassView?.sendSMSResult()
                    }

                    override fun onFailed(code: String, message: String) {
                    }

                }
        )
    }

    override fun onDistroy() {
        iForgetPassView = null
        forgetPassLoader = null
        smsLoader = null
    }

    fun isOk() {
        iForgetPassView?.apply {
            if (phoneOk && codeOk && passwdOk) {
                getBtnNext().isEnabled = true
                getBtnNext().setBackgroundColor(ContextCompat.getColor(SmApplication.getApp(), R.color.red))
            } else {
                getBtnNext().isEnabled = false
                getBtnNext().setBackgroundColor(ContextCompat.getColor(SmApplication.getApp(), R.color.grayBtn))
            }
        }

    }
}