package com.sunnyfor.zy.title

import android.content.Context
import android.view.View
import android.widget.TextView
import com.sunnyfor.zy.R

/**
 * Desc 默认的标题栏Bean
 * Author ZhangYe
 * Mail yongzuo.chen@foxmail.com
 * Date 2020/4/28 00:23
 */
class DefaultTitleBean(context: Context) : BaseTitleBean(context,R.layout.zy_layout_default_title) {

    fun setTitle(title: String) {
        getView()?.findViewById<TextView>(R.id.tv_title)?.text = title
    }

    fun setLeftOnClickListener(listener: View.OnClickListener) {
        getView()?.findViewById<TextView>((R.id.tv_left))?.setOnClickListener(listener)
    }
}