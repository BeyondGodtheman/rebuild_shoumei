package com.shoumei.xhn.setting.view

import android.view.View
import com.shoumei.xhn.BuildConfig
import com.shoumei.xhn.R
import com.shoumei.xhn.base.BaseActivity
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.activity_base.*

/**
 * 关于首媒
 * Created by zhangye on 2018/9/10.
 */
class AboutActivity: BaseActivity() {
    override fun setLayout(): Int = R.layout.activity_about

    override fun initView() {
        frameTitle.addView( titleManager.defaultTitle("关于首媒"))
        tvDesc.text = ("版本号：首媒${BuildConfig.VERSION_NAME}")
    }

    override fun update() {
    }

    override fun loadData() {
    }

    override fun close() {
    }

    override fun onClick(p0: View?) {
    }
}