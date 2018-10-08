package com.shoumei.xhn.mine.presenter

import com.shoumei.xhn.R
import com.shoumei.xhn.SmApplication
import com.shoumei.xhn.base.BaseModel
import com.shoumei.xhn.base.BasePresenter
import com.shoumei.xhn.http.ApiManager
import com.shoumei.xhn.mine.data.MineLoader
import com.shoumei.xhn.mine.model.CommonData
import com.shoumei.xhn.mine.model.MineNavEntity
import com.shoumei.xhn.mine.view.IMineView

/**
 * 我的页面的逻辑处理
 * Created by zhangye on 2018/8/28.
 */
class MinePresenter(private var iMineView: IMineView?) : BasePresenter<IMineView>(iMineView) {
    private val topTitles = ArrayList<MineNavEntity>()
    private val bottomTitles = ArrayList<MineNavEntity>()
    private var mineLoader: MineLoader? = null

    override fun onCreate() {

        mineLoader = MineLoader(composites)

        topTitles.add(MineNavEntity(SmApplication.getApp().getString(R.string.iconMineCollection), "收藏"))
        topTitles.add(MineNavEntity(SmApplication.getApp().getString(R.string.iconMineWatch), "关注"))
        topTitles.add(MineNavEntity(SmApplication.getApp().getString(R.string.iconMineFan), "粉丝"))
        topTitles.add(MineNavEntity(SmApplication.getApp().getString(R.string.iconMineHome), "档案"))

        iMineView?.topNavData(topTitles)
        loadDefault()
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

    //加载通用数据
    fun loadCommonData(){
        iMineView?.showLoading()
        mineLoader?.loadCommonData(object :ApiManager.OnResult<BaseModel<CommonData>>(){
            override fun onSuccess(data: BaseModel<CommonData>) {
                iMineView?.hideLoading()
                iMineView?.onCommonData(data)
            }

            override fun onFailed(code: String, message: String) {
                iMineView?.hideLoading()
            }

        })
    }



    override fun onClose() {
        iMineView = null
        mineLoader = null
    }

}