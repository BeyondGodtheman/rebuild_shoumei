package com.coco_sh.shmstore.setting

import android.view.View
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.base.BaseActivity
import com.coco_sh.shmstore.login.manager.UserManager
import com.coco_sh.shmstore.title.TitleManager
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * 设置页面
 * Created by zhangye on 2018/8/24.
 */
class SettingActivity : BaseActivity() {
    override fun setLayout(): Int = R.layout.activity_setting

    override fun initView() {
        frameTitle.addView(TitleManager(this).defaultTitle("设置"))
        btnLogOut.setOnClickListener(this)
    }

    override fun update() {
    }

    override fun loadData() {
    }

    override fun close() {
    }

    override fun onClick(view: View) {
        when (view.id) {
            btnLogOut.id -> {
                UserManager.setEmptyUser()
                finish()
            }

        }
    }
}