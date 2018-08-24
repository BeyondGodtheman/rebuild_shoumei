package com.coco_sh.shmstore.setting

import android.view.View
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.base.BaseActivity
import com.coco_sh.shmstore.title.TitleManager
import kotlinx.android.synthetic.main.activity_base.*

/**
 * 设置页面
 * Created by zhangye on 2018/8/24.
 */
class SettingActivity: BaseActivity() {
    override fun setLayout(): Int = R.layout.activity_setting

    override fun initView() {
        frameTitle.addView(TitleManager(this).defaultTitle("设置"))
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