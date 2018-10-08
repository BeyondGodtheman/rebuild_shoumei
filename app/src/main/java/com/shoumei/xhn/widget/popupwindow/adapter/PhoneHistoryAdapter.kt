package com.shoumei.xhn.widget.popupwindow.adapter

import android.view.View
import android.view.ViewGroup
import com.shoumei.xhn.R
import com.shoumei.xhn.base.BaseRecycleAdapter
import com.shoumei.xhn.base.BaseRecycleViewHolder
import com.shoumei.xhn.login.model.LoginHistory
import kotlinx.android.synthetic.main.item_phone_history_layout.view.*

/**
 * 手机号码历史记录的Adapter
 * Created by zhangye on 2018/8/24.
 */
class PhoneHistoryAdapter(list: ArrayList<LoginHistory>) : BaseRecycleAdapter<LoginHistory>(list) {
    override fun onBindViewHolder(holder: BaseRecycleViewHolder, position: Int) {
        holder.itemView.tvPhone.text = getData(position).phone
        holder.itemView.tvDelete.setOnClickListener {
            onItemDeleteListener?.onItemDelete(position)
        }
    }

    override fun setLayout(parent: ViewGroup, viewType: Int): View = View.inflate(context, R.layout.item_phone_history_layout, null)


    interface OnItemDeleteListener {
        fun onItemDelete(position: Int)
    }

    private var onItemDeleteListener: OnItemDeleteListener? = null


    fun setOnItemDeleteListener(listener: OnItemDeleteListener) {
        onItemDeleteListener = listener
    }
}