package com.shoumei.xhn.home.presenter

import com.shoumei.xhn.base.BasePresenter
import com.shoumei.xhn.home.view.IWeatherView

/**
 * Created by zhangye on 2018/8/3.
 */
class WeatherPresenter(private var iWeather: IWeatherView):BasePresenter<IWeatherView>(iWeather) {
    override fun onClose() {
    }

    override fun onCreate() {

    }
}