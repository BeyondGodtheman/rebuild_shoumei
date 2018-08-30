package com.coco_sh.shmstore.setting

import android.view.View
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.base.BaseActivity
import com.coco_sh.shmstore.dialog.SmediaDialog
import com.coco_sh.shmstore.login.manager.UserManager
import com.coco_sh.shmstore.title.TitleManager
import com.coco_sh.shmstore.utils.GlideApp
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
        UserManager.getCommon()?.let {
            tvName.text = it.nickname
            tvNo.text = (getString(R.string.no) + it.smno)
            isvPhoto.setNoIconValue(it.phone)

            GlideApp.with(this)
                    .load(it.avatar)
                    .placeholder(R.drawable.defalut_updata_image)
                    .into(ivHead)
        }
    }

    override fun loadData() {
    }

    override fun close() {
    }

    override fun onClick(view: View) {
        when (view.id) {
            btnLogOut.id -> logout() //退出登录

        }
    }


    //退出弹窗显示
    private fun logout() {
        val dialog = SmediaDialog(this)
        dialog.setTitle("是否要退出当前账号")
        dialog.OnClickListener = View.OnClickListener {
            UserManager.setEmptyUser()
            finish()
        }
        dialog.show()
    }
}