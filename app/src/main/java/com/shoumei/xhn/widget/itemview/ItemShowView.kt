package com.shoumei.xhn.widget.itemview

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.RelativeLayout
import com.shoumei.xhn.R
import kotlinx.android.synthetic.main.layout_item_show.view.*

/**
 * 展示key value数据条目
 * Created by zhangye on 2018/3/29.
 */
class ItemShowView : RelativeLayout {
    constructor(context: Context?) : this(context, null)
    constructor(context: Context?, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        initView(attrs)
    }

    private var value:String = ""


    fun initView(attrs: AttributeSet?) {
        LayoutInflater.from(context).inflate(R.layout.layout_item_show, this, true)
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(it, R.styleable.ItemShowView)
            val name = typedArray.getString(R.styleable.ItemShowView_nameText)
            typedArray.recycle()
            if (name != null) {
                tvName.text = name
            }
        }
    }


    fun setValue(value: String) {
        setValue(value,resources.getString(R.string.iconMore))
    }

    fun setValue(value: String, iconStr: String) {
        this.value = value
        tvIcon.text = ("$value  $iconStr")
    }


    fun getValue(): String = value

}