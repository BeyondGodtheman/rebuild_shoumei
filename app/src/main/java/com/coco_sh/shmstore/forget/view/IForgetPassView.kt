package com.coco_sh.shmstore.forget.view

import android.widget.Button
import android.widget.EditText
import com.coco_sh.shmstore.base.IBaseView
import com.coco_sh.shmstore.widget.button.TimerButton

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