package com.shoumei.xhn.widget.popupwindow

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.PopupWindow
import com.shoumei.xhn.R
import com.shoumei.xhn.SmApplication
import com.shoumei.xhn.click.OnItemClickListener
import com.shoumei.xhn.widget.dialog.SmediaDialog
import com.shoumei.xhn.login.model.LoginHistory
import com.shoumei.xhn.widget.popupwindow.adapter.PhoneHistoryAdapter
import kotlinx.android.synthetic.main.layout_phone_history.view.*

/**
 * 登录历史手机号码弹窗
 * Created by zhangye on 2018/8/24.
 */
class HistoryPhonePopupWindow(context: Context, var data: ArrayList<LoginHistory>, parent: View) : PopupWindow(context) {

    private val view: View by lazy {
        View.inflate(context, R.layout.layout_phone_history, null)
    }

    private val phoneHistoryAdapter: PhoneHistoryAdapter by lazy {
        PhoneHistoryAdapter(data)
    }

    init {
        view.recyclerView.layoutManager = LinearLayoutManager(context)

        phoneHistoryAdapter.setOnItemDeleteListener(object : PhoneHistoryAdapter.OnItemDeleteListener {
            override fun onItemDelete(position: Int) {
                showDeleteDialog(context, position)
            }
        })

        view.recyclerView.adapter = phoneHistoryAdapter
        contentView = view
        this.width = parent.width
        this.height = (context.resources.getDimension(R.dimen.h600).toInt())
        this.setBackgroundDrawable(ColorDrawable(0))
        this.isFocusable = true
        this.isOutsideTouchable = true
    }


    //显示删除弹窗
    fun showDeleteDialog(context: Context, position: Int) {
        val dialog = SmediaDialog(context)
        dialog.setTitle("是否清除该号码")
        dialog.OnClickListener = View.OnClickListener {
            val history = data[position]
            SmApplication.getApp().dataStorage.delete(history)
            data.remove(history)
            phoneHistoryAdapter.notifyDataSetChanged()
            onDeleteLisenter?.onDeleteResult(history)
        }
        dialog.show()
    }

    //删除记录回调
    var onDeleteLisenter: OnDeleteListener? = null

    interface OnDeleteListener {
        fun onDeleteResult(loginHistory: LoginHistory)
    }


    fun setOnItemClickListener(onItemClickListener: OnItemClickListener) {
        phoneHistoryAdapter.setOnItemClickListener(onItemClickListener)
    }
}