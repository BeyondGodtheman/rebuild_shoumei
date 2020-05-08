package com.shoumei.xhn.widget.textView

import android.content.Context
import android.util.AttributeSet
import com.shoumei.xhn.SmApplication

/**
 * 图标字体的TextView
 * Created by ZhangYe on 2018/1/16.
 */
open class IconTextView : android.support.v7.widget.AppCompatTextView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        typeface = SmApplication.getApp().iconFontType
    }

    /**
     * 字体加粗
     */
    fun boldText() {
        paint.isFakeBoldText = true
    }
}