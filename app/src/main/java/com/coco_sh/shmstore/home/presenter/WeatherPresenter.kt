package com.coco_sh.shmstore.home.presenter

import com.coco_sh.shmstore.base.BasePresenter
import com.coco_sh.shmstore.home.view.IWeatherView

/**
 * Created by zhangye on 2018/8/3.
 */
class WeatherPresenter(private var iWeather: IWeatherView):BasePresenter<IWeatherView>(iWeather) {
    override fun onClose() {
    }

    override fun onCreate() {

    }
}