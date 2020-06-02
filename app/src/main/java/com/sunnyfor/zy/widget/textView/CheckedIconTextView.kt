package com.sunnyfor.zy.widget.textView

import android.content.Context
import android.support.v4.content.ContextCompat
import android.util.AttributeSet
import android.view.View
import android.widget.CompoundButton
import com.sunnyfor.zy.R


/**
 * 实现Checked功能
 * Created by zhangye on 2018/1/24.
 */
class CheckedIconTextView : IconTextView, View.OnClickListener {
    private var isChecked = false
    private var listener: CompoundButton.OnCheckedChangeListener? = null
    private var isAuto = true

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        setOnClickListener(this)
    }


    fun setOnCheckedChangeListener(listener: CompoundButton.OnCheckedChangeListener) {
        this.listener = listener
    }

    override fun onClick(v: View?) {
        if (!isChecked) {
            setChecked(true)
        } else {
            setChecked(false)
        }

        listener?.onCheckedChanged(null, isChecked)
    }

    fun setChecked(isChecked: Boolean) {
        this.isChecked = isChecked
        if (isAuto) {
            text = if (isChecked) {
                setTextColor(ContextCompat.getColor(context, R.color.red))
                resources.getString(R.string.iconChecked)
            } else {
                setTextColor(ContextCompat.getColor(context, R.color.gray9))
                resources.getString(R.string.iconUnChecked)
            }
        }
    }


}