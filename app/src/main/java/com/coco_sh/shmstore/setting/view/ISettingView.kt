package com.coco_sh.shmstore.setting.view

import com.coco_sh.shmstore.base.IBaseView

/**
 * 设置页面View接口
 * Created by zhangye on 2018/9/18.
 */
interface ISettingView : IBaseView {
    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    //显示缓存数量
    fun showCacheSize(size: String)


    fun showMessage(message:String)
}