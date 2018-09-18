package com.coco_sh.shmstore.setting.view

import android.content.Intent
import android.view.View
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.base.BaseActivity
import com.coco_sh.shmstore.dialog.SmediaDialog
import com.coco_sh.shmstore.login.manager.UserManager
import com.coco_sh.shmstore.setting.presenter.SettingPresenter
import com.coco_sh.shmstore.title.TitleManager
import com.coco_sh.shmstore.utils.GlideApp
import com.coco_sh.shmstore.utils.LoadingUtil
import com.coco_sh.shmstore.utils.ToastUtil
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * 设置页面
 * Created by zhangye on 2018/8/24.
 */
class SettingActivity : BaseActivity(), ISettingView {

    private var settingPresenter: SettingPresenter? = null
    private var loadingUtil: LoadingUtil? = null

    private var paypass = 0 //是否设置过支付密码

    override fun setLayout(): Int = R.layout.activity_setting

    override fun initView() {
        frameTitle.addView(TitleManager(this).defaultTitle("设置"))
        loadingUtil = LoadingUtil(frameBody)
        settingPresenter = SettingPresenter(this)
        settingPresenter?.onCreate()
        settingPresenter?.getCacheSize() //获取缓存大小
        //注册点击事件
        resetPayPwd.setOnClickListener(this)
        isvCache.setOnClickListener(this)
        isvAbout.setOnClickListener(this)
        btnLogOut.setOnClickListener(this)
    }

    override fun update() {
        UserManager.getCommon()?.let {
            tvName.text = it.nickname
            tvNo.text = (getString(R.string.no) + it.smno)
            paypass = it.paypass
            isvPhoto.setNoIconValue(it.phone)
            GlideApp.with(this)
                    .load(it.avatar)
                    .placeholder(R.drawable.defalut_updata_image)
                    .into(ivHead)
        }
    }

    override fun loadData() {
    }

    override fun showCacheSize(size: String) {
        isvCache.setNoIconValue(size)
    }

    override fun showMessage(message: String) {
        ToastUtil.show(message)
    }

    override fun close() {
        loadingUtil = null
    }

    override fun onClick(view: View) {
        when (view.id) {
            resetPayPwd.id -> motifyPay() //修改支付密码
            isvCache.id -> clear() //清楚缓存
            isvAbout.id -> startActivity(Intent(this, AboutActivity::class.java))
            btnLogOut.id -> logout() //退出登录
        }
    }


    private fun motifyPay() {
        if (paypass == 0) {
            val dialog = SmediaDialog(this)
            dialog.setTitle("您未设置过支付密码，设置前将验证您的身份，即将发送验证码到" + UserManager.getCryptogramPhone())
            dialog.OnClickListener = View.OnClickListener {

            }
            dialog.show()
        } else {

        }
    }


    //清理缓存弹窗显示
    private fun clear() {
        val dialog = SmediaDialog(this)
        dialog.setTitle("您确定要清除缓存吗?")
        dialog.OnClickListener = View.OnClickListener {
            settingPresenter?.clearCache() //清楚缓存
        }
        dialog.show()

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