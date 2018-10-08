package com.shoumei.xhn.title

import android.content.Intent
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.RelativeLayout
import com.shoumei.xhn.R
import com.shoumei.xhn.base.BaseActivity
import com.shoumei.xhn.login.manager.UserManager
import com.shoumei.xhn.login.view.LoginActivity
import com.shoumei.xhn.setting.view.SettingActivity
import kotlinx.android.synthetic.main.layout_default_title.view.*

/**
 *
 * Created by zhangye on 2018/8/2.
 */
class TitleManager(private var baseActivity: BaseActivity) {

    fun defaultTitle(title: String): View {
        val view = View.inflate(baseActivity, R.layout.layout_default_title, null)
        view.tvTitle.text = title
        view.tvLeft.setOnClickListener(getBackClickListener())
        return view
    }

    fun loginTitle(): View {
        val view = defaultTitle("")
        view.tvLeft.text = baseActivity.getString(R.string.iconClose)
        (view.tvLeft.layoutParams as RelativeLayout.LayoutParams).apply {
            leftMargin = baseActivity.resources.getDimension(R.dimen.w42).toInt()
        }
        return view
    }


    //我的页面title
    fun mineTitle(): View {
        val view = defaultTitle("")
        view.tvLeft.visibility = View.GONE
        view.viewLine.visibility = View.GONE
        view.tvRight.text = baseActivity.getString(R.string.iconSetting)
        view.tvRight.setTextColor(ContextCompat.getColor(baseActivity, R.color.white))
        view.tvRight.setOnClickListener {
            if (UserManager.isLogin()){
                baseActivity.startActivity(Intent(baseActivity, SettingActivity::class.java))
            }else{
                baseActivity.startActivity(Intent(baseActivity, LoginActivity::class.java))
            }
        }
        return view
    }


    private fun getBackClickListener(): View.OnClickListener = View.OnClickListener {
        baseActivity.finish()
    }
}