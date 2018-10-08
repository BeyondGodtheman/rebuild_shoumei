package com.shoumei.xhn.forget.view

import android.widget.Button
import android.widget.EditText
import com.shoumei.xhn.base.IBaseView
import com.shoumei.xhn.widget.button.TimerButton

/**
 * 找回密码View
 * Created by zhangye on 2018/8/3.
 */
interface IForgetPassView : IBaseView {

    fun getEditPhone():EditText

    fun getEditCode():EditText

    fun getEditPass():EditText

    fun getBtnSend():TimerButton

    fun getBtnNext():Button

    fun sendSMSResult()

    fun forgetResult()
}