package com.shoumei.xhn.setting.view

import com.shoumei.xhn.base.IBaseView

/**
 * 设置页面View接口
 * Created by zhangye on 2018/9/18.
 */
interface ISettingView : IBaseView {
    //显示缓存数量
    fun showCacheSize(size: String)


    fun showMessage(message:String)
}