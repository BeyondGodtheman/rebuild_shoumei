package com.coco_sh.shmstore.mine.view

import android.view.View
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.base.BaseFragment
import com.coco_sh.shmstore.title.TitleManager
import kotlinx.android.synthetic.main.fragment_base.view.*


class MineFragment : BaseFragment() {
    override fun setLayout(): Int = R.layout.fragment_mine

    override fun initView() {
        immersionTitle()
        getLayoutView()?.iframeTitle?.apply {
            visibility = View.VISIBLE
            addView(TitleManager(getBaseActivity()).mineTitle()) //加载title
        }

    }

    override fun update() {
    }

    override fun loadData() {
    }

    override fun onClick(p0: View?) {

    }

}