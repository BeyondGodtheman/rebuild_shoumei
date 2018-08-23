package com.coco_sh.shmstore.regist.view

import com.coco_sh.shmstore.base.BaseModel
import com.coco_sh.shmstore.base.IBaseView
import com.coco_sh.shmstore.sms.model.SmSMS

/**
 * 注册View
 * Created by zhangye on 2018/8/2.
 */
interface IRegisterView : IBaseView {

    fun codeBtnSata(boolean: Boolean)

    fun registBtnSata(boolean: Boolean)

    fun snedSMSResult(sms: BaseModel<SmSMS>)

    fun registResult()
}