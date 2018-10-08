package com.shoumei.xhn.login.view

import com.shoumei.xhn.base.BaseModel
import com.shoumei.xhn.base.IBaseView
import com.shoumei.xhn.login.model.Login
import com.shoumei.xhn.login.model.LoginHistory

/**
 *
 * Created by zhangye on 2018/8/2.
 */
interface ILoginView : IBaseView {

    //返回登录信息
    fun loginResult(result: BaseModel<Login>, isOtherLogin:Boolean)

    //返回历史帐号记录数据
    fun onHistory(history: ArrayList<LoginHistory>)

    //登录按钮状态
    fun loginBtnState(boolean: Boolean)

}