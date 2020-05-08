package com.shoumei.xhn.title

import android.content.Context
import android.view.View
import android.view.ViewGroup

/**
 * Desc
 * Author ZhangYe
 * Mail yongzuo.chen@foxmail.com
 * Date 2020/4/28 00:25
 */
abstract class BaseTitleBean(var context: Context,var layoutRes:Int) {

    private var mView: View? = null


    init {
        mView = View.inflate(context, layoutRes, null)
    }


    fun getView() = mView

    /**
     * 显示View
     * @param titleParent 显示view的容器
     */
    fun showView(titleParent: ViewGroup) {
        titleParent.removeAllViews()
        titleParent.addView(mView)
    }
}