package com.coco_sh.shmstore.home.presenter

import com.coco_sh.shmstore.base.BasePresenter
import com.coco_sh.shmstore.home.model.Location
import com.coco_sh.shmstore.home.view.IHomeView
import com.coco_sh.shmstore.utils.LocationUtil

/**
 *
 * Created by zhangye on 2018/8/3.
 */
class HomePresenter(private var iHomeView: IHomeView) : BasePresenter<IHomeView>(iHomeView) {

    private var locationUtil: LocationUtil? = null
    private lateinit var locationListener: LocationUtil.LocationListener

    override fun onCreate() {
        locationUtil = LocationUtil()
        locationListener = object :LocationUtil.LocationListener{
            override fun onLocationChanged(location: Location) {
                iHomeView.getCityView().text = location.city
            }
        }
    }

    //启动定位需要权限
    fun startLocation() {
        locationUtil?.startLocation(locationListener)
    }


    override fun onDistroy() {
        locationUtil = null
    }

}