package com.shoumei.xhn.base

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.shoumei.xhn.R
import com.shoumei.xhn.title.TitleManager
import com.shoumei.xhn.utils.ToastUtil
import com.shoumei.xhn.widget.utils.OverlayViewUtils
import kotlinx.android.synthetic.main.activity_base.*


/**
 *
 * Created by ZhangYe on 2018/8/2.
 */
@SuppressLint("SourceLockedOrientationActivity")
abstract class BaseActivity : AppCompatActivity(), IBaseView, View.OnClickListener {

    private var savedInstanceState: Bundle? = null

    private val overlayViewBean = OverlayViewUtils()

    lateinit var titleManager: TitleManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.savedInstanceState = savedInstanceState
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT //强制屏幕
        titleManager = TitleManager(this)
        setContentView(R.layout.activity_base)
        val bodyView = LayoutInflater.from(this).inflate(setLayout(), null, false)
        frameBody.addView(bodyView)
        initView()
        loadData()
    }

    /**
     * 设置布局操作
     */
    abstract fun setLayout(): Int

    /**
     * 初始化View操作
     */
    abstract fun initView()

    /**
     * 加载数据操作
     */
    abstract fun loadData()

    /**
     * 点击事件回调
     */
    abstract fun onClickEvent(view: View)

    /**
     * 获取title容器
     */
    fun getFrameTitle(): ViewGroup = frameTitle

    /**
     * 显示loading覆盖层
     */
    override fun showLoading() {
        overlayViewBean.showView(frameBody, overlayViewBean.loading)
    }

    /**
     * 隐藏loading覆盖层
     */
    override fun hideLoading() {
        overlayViewBean.hideView(frameBody, overlayViewBean.loading)
    }

    /**
     * 显示error覆盖层
     */
    override fun showError(errorType: ErrorViewType) {
        overlayViewBean.showView(frameBody, overlayViewBean.networkError)
    }

    /**
     * 隐藏error覆盖层
     */
    override fun hideError() {
        overlayViewBean.hideView(frameBody, overlayViewBean.networkError)
    }

    /**
     * 显示Toast
     */
    override fun showMessage(message: String) {
        ToastUtil.show(message)
    }

    /**
     * 注册点击事件
     * @param views 注册事件的View
     */
    fun setOnClickListener(vararg views: View) {
        views.forEach {
            it.setOnClickListener(this)
        }
    }

    /**
     * 点击事件
     */
    override fun onClick(view: View) {
        onClickEvent(view)
    }
}