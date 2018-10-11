package com.shoumei.xhn.splash.view

import android.content.Intent
import android.net.Uri
import android.provider.Settings
import android.view.View
import com.shoumei.xhn.R
import com.shoumei.xhn.base.BaseActivity
import com.shoumei.xhn.home.view.MainActivity
import com.shoumei.xhn.splash.presenter.SplashActivityPresenter
import com.shoumei.xhn.utils.PermissionUtil
import com.shoumei.xhn.widget.dialog.SmediaDialog
import kotlinx.android.synthetic.main.activity_base.*

/**
 * 欢迎引导页面
 * Created by zhangye on 2018/8/3.
 */
class SplashActivity : BaseActivity(), ISplashView {

    private var splashActivityPresenter = SplashActivityPresenter(this)
    private var retry = false //权限重试

    private val permissionUtil:PermissionUtil by lazy {
        PermissionUtil(this)
    }

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
        frameTitle.visibility = View.GONE
    }

    override fun update() {
        if (retry){
            retry = false
            loadData()
        }
    }

    override fun loadData() {
        if (permissionUtil.allPermission()){
            splashActivityPresenter.onCreate()
        }
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (permissionUtil.checkPermission(permissions)){
            splashActivityPresenter.onCreate()
        }else{
            //拒绝权限后弹窗
            val dialog = SmediaDialog(this)
            dialog.setTitle("温馨提示")
            dialog.setDescColor(R.color.gray9)
            dialog.setDesc("${permissionUtil.getMissPerName()}权限是必需的，否则我们无法为您提供核心服务")
            dialog.setCancelText("残忍拒绝")
            dialog.setPositiveText("授权")
            dialog.cancelOnClickListener =  View.OnClickListener{
                dialog.dismiss()
                finish()
            }

            dialog.onClickListener1 = View.OnClickListener {
                //用户勾选不在提醒，跳转至应用设置页面
                if (permissionUtil.isNotReminding()){
                    val intent = Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS)
                    val uri = Uri.fromParts("package", applicationContext.packageName, null)
                    intent.data = uri
                    startActivity(intent)
                    retry = true
                }else{
                    permissionUtil.allPermission()
                }
            }

            dialog.show()
        }
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