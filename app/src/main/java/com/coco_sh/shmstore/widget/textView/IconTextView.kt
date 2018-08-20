package com.coco_sh.shmstore.widget.textView

import android.content.Context
import android.util.AttributeSet
import android.widget.TextView
import com.coco_sh.shmstore.SmApplication

/**
 * 图标字体的TextView
 * Created by zhangye on 2018/1/16.
 */
open class IconTextView : TextView {
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