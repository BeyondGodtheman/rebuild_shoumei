package com.coco_sh.shmstore.title

import android.view.View
import android.widget.RelativeLayout
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.base.BaseActivity
import kotlinx.android.synthetic.main.layout_default_title.view.*

/**
 *
 * Created by zhangye on 2018/8/2.
 */
class TitleManager(private var baseActivity: BaseActivity) {

    fun defaultTitle(title: String): View {
        val view = View.inflate(baseActivity, R.layout.layout_default_title, null)
        view.tvTitle.text = title
        view.setOnClickListener(getBackClickListener())
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


    private fun getBackClickListener(): View.OnClickListener = View.OnClickListener {
        baseActivity.finish()
    }
}