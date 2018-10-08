package com.shoumei.xhn.regist.view

import com.shoumei.xhn.base.BaseModel
import com.shoumei.xhn.base.IBaseView
import com.shoumei.xhn.sms.model.SmSMS

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