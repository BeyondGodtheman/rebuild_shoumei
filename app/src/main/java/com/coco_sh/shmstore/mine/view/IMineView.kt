package com.coco_sh.shmstore.mine.view

import com.coco_sh.shmstore.base.IBaseView
import com.coco_sh.shmstore.mine.model.MineNavEntity

/**
 * 我的页面view类
 * Created by zhangye on 2018/8/28.
 */
interface IMineView : IBaseView {

    fun topNavData(list: ArrayList<MineNavEntity>) //顶部导航数据

    fun buttomNavData(list: ArrayList<MineNavEntity>)//菜单数据
}