package com.coco_sh.shmstore.home.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.bumptech.glide.Glide
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.click.OnItemClickListener
import com.coco_sh.shmstore.home.model.Banner
import kotlinx.android.synthetic.main.item_card_view.view.*

/**
 * 首页bannerAdapter
 * Created by zhangye on 2018/1/11.
 */
class HomeBannerAdapter(var context: Context, var list: ArrayList<Banner.Data>) : PagerAdapter() {

    var onItemClickListener: OnItemClickListener? = null

    init {
        if (list.size >= 1) {
            list.add(0, list[list.size - 1])
            list.add(0, list[list.size - 2])
            list.add(list[2])
            list.add(list[3])
        }
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean = view == `object`

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view = View.inflate(context, R.layout.item_card_view, null)
        Glide.with(context).load(list[position].redPacketImg).into(view.imageView)
        container.addView(view)
        view.setOnClickListener {
            onItemClickListener?.onItemClick(it, position)
        }
        return view
    }

    override fun getCount(): Int = list.size

    override fun getPageTitle(position: Int): String? = list[position].redPacketName
}