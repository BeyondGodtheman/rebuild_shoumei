package com.coco_sh.shmstore.splash.view

import android.content.Intent
import android.view.View
import com.coco_sh.shmstore.home.view.MainActivity
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.base.BaseActivity
import com.coco_sh.shmstore.splash.presenter.SplashActivityPresenter
import kotlinx.android.synthetic.main.activity_base.*

/**
 * 欢迎引导页面
 * Created by zhangye on 2018/8/3.
 */
class SplashActivity : BaseActivity(), ISplashView {

    private var splashActivityPresenter = SplashActivityPresenter(this)

    //倒计时结束
    override fun overTime() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }


    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun setLayout(): Int = R.layout.activity_splash

    override fun initView() {
        splashActivityPresenter.onCreate()
        frameTitle.visibility = View.GONE
    }

    override fun update() {
    }

    override fun loadData() {
    }


    override fun onClick(p0: View?) {

    }

    //拦截返回键
    override fun onBackPressed() {
    }

    override fun close() {
        splashActivityPresenter.onDestroy()
    }
}