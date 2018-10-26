package com.shoumei.xhn.base


/**
 *
 * Created by zhangye on 2018/8/2.
 */
interface IBaseView {

    fun showLoading()

    fun hideLoading()

    fun showError(errorType: ErrorViewType)

    fun hideError()

    fun showMessage(message:String)
}