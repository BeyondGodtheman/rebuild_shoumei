package com.coco_sh.shmstore.login.view

import android.widget.Button
import android.widget.EditText
import com.coco_sh.shmstore.base.BaseModel
import com.coco_sh.shmstore.base.IBaseView
import com.coco_sh.shmstore.login.model.Login
import com.coco_sh.shmstore.login.model.LoginHistory

/**
 *
 * Created by zhangye on 2018/8/2.
 */
interface ILoginView : IBaseView {

    //返回登录信息
    fun loginResult(result: BaseModel<Login>, isOtherLogin:Boolean)

    //返回历史帐号记录数据
    fun onHistory(history: List<LoginHistory>)

    //隐藏下拉菜单
    fun gonePhone(history: LoginHistory)

    //帐号输入框
    fun getEditPhone():EditText

    //密码输入框
    fun getEditPass():EditText

    //登录按钮
    fun getBtnLogin():Button

}