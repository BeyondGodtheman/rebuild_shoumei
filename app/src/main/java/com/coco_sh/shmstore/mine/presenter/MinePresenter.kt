package com.coco_sh.shmstore.mine.presenter

import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.SmApplication
import com.coco_sh.shmstore.base.BasePresenter
import com.coco_sh.shmstore.mine.model.MineNavEntity
import com.coco_sh.shmstore.mine.view.IMineView

/**
 * 我的页面的逻辑处理
 * Created by zhangye on 2018/8/28.
 */
class MinePresenter(private var iMineView: IMineView?) : BasePresenter<IMineView>(iMineView) {
    private val topTitles = ArrayList<MineNavEntity>()
    private val bottomTitles = ArrayList<MineNavEntity>()

    override fun onCreate() {
        topTitles.add(MineNavEntity(SmApplication.getApp().getString(R.string.iconMineCollection), "收藏"))
        topTitles.add(MineNavEntity(SmApplication.getApp().getString(R.string.iconMineWatch), "关注"))
        topTitles.add(MineNavEntity(SmApplication.getApp().getString(R.string.iconMineFan), "粉丝"))
        topTitles.add(MineNavEntity(SmApplication.getApp().getString(R.string.iconMineHome), "档案"))

        iMineView?.topNavData(topTitles)

//        if (!UserManager.isLogin()){
        loadDefault()
//        }

    }


    private fun loadDefault() {
        bottomTitles.clear()
        bottomTitles.add(MineNavEntity(SmApplication.getApp().getString(R.string.iconMineAuthen), "认证"))
        bottomTitles.add(MineNavEntity(SmApplication.getApp().getString(R.string.iconMinePurse), "钱包"))
        bottomTitles.add(MineNavEntity(SmApplication.getApp().getString(R.string.iconMineOrder), "订单"))
        bottomTitles.add(MineNavEntity(SmApplication.getApp().getString(R.string.iconMineOrder), "发出的红包"))
        bottomTitles.add(MineNavEntity(SmApplication.getApp().getString(R.string.iconMineHelp), "帮助中心"))
        bottomTitles.add(MineNavEntity(SmApplication.getApp().getString(R.string.iconAddress), "地址管理"))
        iMineView?.buttomNavData(bottomTitles)
    }

    override fun onDistroy() {
        iMineView = null

    }

}