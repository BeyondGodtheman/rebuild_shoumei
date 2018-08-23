package com.coco_sh.shmstore.utils

import android.view.View
import android.view.ViewGroup
import com.coco_sh.shmstore.R

/**
 * 加载view工具类
 * Created by zhangye on 2018/8/23.
 */
class LoadingUtil(private var bodyView: ViewGroup) {

    private val loadingView: View by lazy {
        View.inflate(bodyView.context, R.layout.layout_loading, null)
    }


    fun showLoading() {
        bodyView.addView(loadingView)
    }

    fun hidenLoading() {
        bodyView.removeView(loadingView)
    }

}