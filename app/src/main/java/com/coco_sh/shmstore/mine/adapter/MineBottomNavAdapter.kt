package com.coco_sh.shmstore.mine.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.base.BaseRecycleAdapter
import com.coco_sh.shmstore.base.BaseRecycleViewHolder
import com.coco_sh.shmstore.mine.model.MineNavEntity
import kotlinx.android.synthetic.main.item_mine_bottom_nav.view.*

/**
 * 我的底部导航适配器
 * Created by zhangye on 2018/8/28.
 */
class MineBottomNavAdapter(list: ArrayList<MineNavEntity>) : BaseRecycleAdapter<MineNavEntity>(list) {

    override fun onBindViewHolder(holder: BaseRecycleViewHolder, position: Int) {
        holder.itemView.tvIcon.text = getData(position).icon
        holder.itemView.tvDesc.text = getData(position).title
        holder.itemView.tag = getData(position).title
    }

    override fun setLayout(parent: ViewGroup, viewType: Int): View = LayoutInflater.from(parent.context).inflate(R.layout.item_mine_bottom_nav,parent,false)

}