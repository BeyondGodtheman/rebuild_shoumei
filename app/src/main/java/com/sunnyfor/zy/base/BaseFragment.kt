package com.sunnyfor.zy.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import com.sunnyfor.zy.R
import com.sunnyfor.zy.widget.utils.OverlayViewUtils
import kotlinx.android.synthetic.main.fragment_base.*
import kotlinx.android.synthetic.main.fragment_base.view.*


/**
 *
 * Created by zhangye on 2018/8/2.
 */
abstract class BaseFragment : Fragment(), IBaseView, View.OnClickListener {
    private var savedInstanceState: Bundle? = null

    private val overlayViewBean = OverlayViewUtils()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.savedInstanceState = savedInstanceState
        val mView = inflater.inflate(R.layout.zy_fragment_base, container, false)
        mView?.iframeBody?.addView(inflater.inflate(setLayout(), container, false))
        initView()
        loadData()
        return mView
    }


    /**
     * 沉浸式Title
     */
    fun immersionTitle() {
        iframeBody?.apply {
            (layoutParams as RelativeLayout.LayoutParams).addRule(RelativeLayout.BELOW, 0)
        }
        iframeTitle?.setBackgroundResource(R.color.transparent)

    }


    fun getBaseActivity(): BaseActivity = requireActivity() as BaseActivity


    override fun onDestroy() {
        super.onDestroy()
        close()
    }


    override fun onResume() {
        super.onResume()
        update()
    }

    abstract fun setLayout(): Int

    abstract fun initView()

    abstract fun update()

    abstract fun loadData()


    override fun showMessage(message: String) {
        getBaseActivity().showMessage(message)
    }

    override fun showLoading() {
        overlayViewBean.showView(iframeBody, overlayViewBean.loading)
    }

    override fun hideLoading() {
        overlayViewBean.hideView(iframeBody, overlayViewBean.loading)
    }

    override fun showError(errorType: ErrorViewType) {
        overlayViewBean.showView(iframeBody, overlayViewBean.networkError)
    }

    override fun hideError() {
        overlayViewBean.hideView(iframeBody, overlayViewBean.networkError)
    }

    abstract fun close()
}