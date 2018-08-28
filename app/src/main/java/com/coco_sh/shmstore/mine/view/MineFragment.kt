package com.coco_sh.shmstore.mine.view

import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.base.BaseFragment
import com.coco_sh.shmstore.mine.adapter.MineBottomNavAdapter
import com.coco_sh.shmstore.mine.adapter.MineTopNavAdapter
import com.coco_sh.shmstore.mine.model.MineNavEntity
import com.coco_sh.shmstore.mine.presenter.MinePresenter
import com.coco_sh.shmstore.title.TitleManager
import com.coco_sh.shmstore.utils.RecycleViewDivider
import kotlinx.android.synthetic.main.fragment_base.view.*
import kotlinx.android.synthetic.main.fragment_mine.view.*


class MineFragment : BaseFragment(), IMineView {

    private val minePresenter: MinePresenter by lazy {
        MinePresenter(this)
    }

    override fun showLoading() {

    }

    override fun hidenLoading() {

    }


    override fun setLayout(): Int = R.layout.fragment_mine

    override fun initView() {
        immersionTitle() //title透明不占位

        getLayoutView()?.iframeTitle?.apply {
            visibility = View.VISIBLE
            addView(TitleManager(getBaseActivity()).mineTitle()) //加载title
        }
        getLayoutView()?.recycleTopNav?.setHasFixedSize(true)
        getLayoutView()?.recycleTopNav?.addItemDecoration(
                RecycleViewDivider(getBaseActivity(), LinearLayoutManager.VERTICAL, resources.getDimension(R.dimen.h3).toInt(), ContextCompat.getColor(getBaseActivity(), R.color.graye0))
        ) //分割线

        getLayoutView()?.recycleBottomNav?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        getLayoutView()?.recycleBottomNav?.addItemDecoration(
                RecycleViewDivider(getBaseActivity(), LinearLayoutManager.HORIZONTAL, resources.getDimension(R.dimen.h3).toInt(), ContextCompat.getColor(getBaseActivity(), R.color.graye0))
        ) //分割线

        minePresenter.onCreate()
    }

    override fun update() {
    }

    override fun loadData() {

    }

    override fun onClick(p0: View?) {

    }


    override fun topNavData(list: ArrayList<MineNavEntity>) {
        getLayoutView()?.recycleTopNav?.layoutManager = GridLayoutManager(context, list.size)
        getLayoutView()?.recycleTopNav?.adapter = MineTopNavAdapter(list)
    }

    override fun buttomNavData(list: ArrayList<MineNavEntity>) {
        getLayoutView()?.recycleBottomNav?.adapter = MineBottomNavAdapter(list)
    }

    override fun close() {
        minePresenter.onDistroy()
    }
}