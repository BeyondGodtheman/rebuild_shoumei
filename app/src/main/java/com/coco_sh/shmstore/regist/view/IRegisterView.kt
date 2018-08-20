package com.coco_sh.shmstore.regist.view

import android.widget.EditText
import com.coco_sh.shmstore.base.BaseModel
import com.coco_sh.shmstore.base.IBaseView
import com.coco_sh.shmstore.sms.model.SmSMS
import com.coco_sh.shmstore.widget.textView.CheckedIconTextView

/**
 * 注册View
 * Created by zhangye on 2018/8/2.
 */
interface IRegisterView : IBaseView {

    fun getEditPhoto():EditText

    fun getEditCode():EditText

    fun getEditPass():EditText

    fun getEditSurePass():EditText

    fun getBtnIsArgeen(): CheckedIconTextView

    fun codeBtnSata(boolean: Boolean)

    fun registBtnSata(boolean: Boolean)

    fun snedSMSResult(sms: BaseModel<SmSMS>)

    fun registResult()
}