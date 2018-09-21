package com.coco_sh.shmstore.home.view

import android.view.View
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.base.BaseActivity
import com.coco_sh.shmstore.base.BasePresenter
import com.coco_sh.shmstore.base.BaseFragment
import com.coco_sh.shmstore.base.IBaseView
import com.coco_sh.shmstore.home.presenter.WeatherPresenter

/**
 * 天气fragment
 * Created by zhangye on 2018/8/3.
 */
class WeatherFragment : BaseFragment(), IWeatherView {
    override fun close() {
    }

    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun setLayout(): Int = R.layout.fragment_weather

    override fun initView() {
    }

    override fun update() {
    }

    override fun loadData() {
    }


    override fun onClick(p0: View?) {
    }
}