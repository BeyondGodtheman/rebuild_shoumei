package com.coco_sh.shmstore.widget.dialog

import android.app.Dialog
import android.content.Context
import android.view.Gravity
import android.view.View
import android.view.Window
import android.widget.RelativeLayout
import com.coco_sh.shmstore.R
import kotlinx.android.synthetic.main.dialog_photo_layout.*


/**
 *
 * Created by lmg on 2018/3/23.
 */
class PhotoDialog(context: Context?) : Dialog(context), View.OnClickListener {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        // 让dialog置于底部
        val window = window!!
        window.decorView.setPadding(0, 0, 0, 0)
        val attributes = window.attributes
        attributes.width = RelativeLayout.LayoutParams.MATCH_PARENT
        attributes.gravity = Gravity.BOTTOM or Gravity.CENTER_HORIZONTAL
        window.attributes = attributes
        window.setBackgroundDrawableResource(R.color.transparent)
        setContentView(R.layout.dialog_photo_layout)

        tvCancel!!.setOnClickListener(this)
        tvUpdate.setOnClickListener(this)
        tvSelect.setOnClickListener(this)
        setCancelable(true)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.tvCancel -> {
            }
            R.id.tvUpdate -> {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener?.onTopClick()
                }
            }
            R.id.tvSelect -> {
                //读取
                if (mOnItemClickListener != null) {
                    mOnItemClickListener?.onBottomClick()
                }
            }
        }
        dismiss()
    }


    interface OnItemClickListener {
        fun onTopClick()
        fun onBottomClick()
    }

    private var mOnItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(mOnItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener
    }


    fun setValue(topValue: String, bottomValue: String) {
        tvUpdate.text = topValue
        tvSelect.text = bottomValue
    }
}

