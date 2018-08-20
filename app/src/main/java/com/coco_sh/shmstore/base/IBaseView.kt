package com.coco_sh.shmstore.base


/**
 *
 * Created by zhangye on 2018/8/2.
 */
interface IBaseView {

    fun showLoading()

    fun hidenLoading()

    fun showError(errorType: ErrorViewType) {}
}