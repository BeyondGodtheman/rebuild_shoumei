package com.shoumei.xhn.splash.presenter

import com.shoumei.xhn.base.BasePresenter
import com.shoumei.xhn.splash.view.ISplashView
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.delay
import kotlinx.coroutines.experimental.launch

/**
 *
 * Created by zhangye on 2018/8/3.
 */
class SplashActivityPresenter(private var iSplashView: ISplashView) : BasePresenter<ISplashView>(iSplashView) {

    private var time = 3 * 1000  //开屏引导时间
    private var job: Job? = null

    override fun onCreate() {

        job = launch(UI) {
            delay(time)
            iSplashView.overTime()
        }
    }

    override fun onClose() {
        job?.cancel()
    }
}