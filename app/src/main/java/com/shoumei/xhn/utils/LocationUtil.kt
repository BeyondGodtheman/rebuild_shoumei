package com.shoumei.xhn.utils

import com.amap.api.location.AMapLocation
import com.amap.api.location.AMapLocationClient
import com.amap.api.location.AMapLocationClientOption
import com.amap.api.location.AMapLocationListener
import com.shoumei.xhn.SmApplication
import com.shoumei.xhn.home.model.Location


/**
 * 高德定位
 * Created by 张野 on 2017/10/18.
 */
class LocationUtil : AMapLocationListener {
    private var mLocationClient: AMapLocationClient = AMapLocationClient(SmApplication.getApp()) //初始化mLocationClient
    private lateinit var listener: LocationListener
    private lateinit var  mLocation:Location
    init {
        //初AMapLocationClientOption对象
        val mOptions = AMapLocationClientOption()
        //获取最近3s内精度最高的一次定位结果
        mOptions.isOnceLocationLatest = true
        mLocationClient.setLocationOption(mOptions)
        //
        //设置定位回调监听
        mLocationClient.setLocationListener(this)

    }

    override fun onLocationChanged(location: AMapLocation) {
        LogUtil.i("定位结果" + location.toStr())
        if (!location.city.isEmpty()) {
            mLocation = Location(
                    location.latitude.toString(),
                    location.longitude.toString(),
                    location.province,
                    location.city,
                    location.address,
                    location.district,
                    location.adCode)
            SmApplication.getApp().setData(DataKey.LOCATION, mLocation)
        }
        listener.onLocationChanged(mLocation)
    }


    fun startLocation(listener: LocationListener) {
        this@LocationUtil.listener = listener
        mLocationClient.startLocation()
    }


    interface LocationListener {
        fun onLocationChanged(location: Location)
    }

}