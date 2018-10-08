package com.shoumei.xhn.mine.view

import com.shoumei.xhn.base.BaseModel
import com.shoumei.xhn.base.IBaseView
import com.shoumei.xhn.mine.model.CommonData
import com.shoumei.xhn.mine.model.MineNavEntity

/**
 * 我的页面view类
 * Created by zhangye on 2018/8/28.
 */
interface IMineView : IBaseView {

    fun topNavData(list: ArrayList<MineNavEntity>) //顶部导航数据

    fun buttomNavData(list: ArrayList<MineNavEntity>)//菜单数据

    fun onCommonData(data:BaseModel<CommonData>) //通用数据回调
}