package com.shoumei.xhn.setting.view

import com.shoumei.xhn.base.IBaseView
import com.shoumei.xhn.mine.model.CommonData

/**
 * 设置页面View接口
 * Created by zhangye on 2018/9/18.
 */
interface ISettingView : IBaseView {
    //显示缓存数量
    fun showCacheSize(size: String)
    //通用档案资料
    fun loadCommonData(commonData: CommonData)
}