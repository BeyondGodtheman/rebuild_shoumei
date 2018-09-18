package com.coco_sh.shmstore.mine.view

import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.request.RequestOptions
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.base.BaseFragment
import com.coco_sh.shmstore.base.BaseModel
import com.coco_sh.shmstore.login.manager.UserManager
import com.coco_sh.shmstore.mine.adapter.MineBottomNavAdapter
import com.coco_sh.shmstore.mine.adapter.MineTopNavAdapter
import com.coco_sh.shmstore.mine.model.CommonData
import com.coco_sh.shmstore.mine.model.MineNavEntity
import com.coco_sh.shmstore.mine.presenter.MinePresenter
import com.coco_sh.shmstore.title.TitleManager
import com.coco_sh.shmstore.utils.GlideApp
import com.coco_sh.shmstore.utils.LoadingUtil
import com.coco_sh.shmstore.utils.RecycleViewDivider
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_base.view.*
import kotlinx.android.synthetic.main.fragment_mine.view.*
import kotlinx.android.synthetic.main.layout_top_head.view.*


class MineFragment : BaseFragment(), IMineView {

    private var loadingUtil: LoadingUtil? = null

    private val minePresenter: MinePresenter by lazy {
        MinePresenter(this)
    }

    override fun showLoading() {
        loadingUtil?.showLoading()
    }

    override fun hidenLoading() {
        loadingUtil?.hidenLoading()
    }


    override fun setLayout(): Int = R.layout.fragment_mine

    override fun initView() {
        getLayoutView()?.iframeBody?.let {
            loadingUtil = LoadingUtil(it)
        }

        immersionTitle() //title透明不占位

        getLayoutView()?.iframeTitle?.apply {
            visibility = View.VISIBLE
            addView(TitleManager(getBaseActivity()).mineTitle()) //加载title
        }
        getLayoutView()?.recycleTopNav?.setHasFixedSize(true)
        //分割线
        getLayoutView()?.recycleTopNav?.addItemDecoration(
                RecycleViewDivider(getBaseActivity(), LinearLayoutManager.VERTICAL, resources.getDimension(R.dimen.h3).toInt(),
                        ContextCompat.getColor(getBaseActivity(), R.color.graye0))
        )

        getLayoutView()?.recycleBottomNav?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        //分割线
        getLayoutView()?.recycleBottomNav?.addItemDecoration(
                RecycleViewDivider(getBaseActivity(), LinearLayoutManager.HORIZONTAL, resources.getDimension(R.dimen.h3).toInt(),
                        ContextCompat.getColor(getBaseActivity(), R.color.graye0))
        )

        minePresenter.onCreate()
    }


    override fun update() {
        if (UserManager.isLogin()) {
            getLayoutView()?.tvName?.text = ""
            minePresenter.loadCommonData() //加载数据
        } else {
            getLayoutView()?.ivHead?.setImageResource(R.drawable.bg_update_head) //默认头像
            getLayoutView()?.ivBg?.setImageResource(R.mipmap.bg_top_head) //默认背景图
            getLayoutView()?.tvName?.text = getString(R.string.visitor)
            getLayoutView()?.tvNo?.visibility = View.GONE
        }
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


    //通用信息回调
    override fun onCommonData(data: BaseModel<CommonData>) {
        data.message?.let {
            UserManager.setCommon(it) //存储本地
            getLayoutView()?.tvName?.text = it.nickname
            getLayoutView()?.tvNo?.apply {
                text = (getString(R.string.no) + it.smno)
                visibility = View.VISIBLE
            }
            getLayoutView()?.ivHead?.let {
                GlideApp.with(this).load(data.message?.avatar).placeholder(R.drawable.bg_update_head).into(it)
            }

            if (data.message?.avatar.isNullOrEmpty()) {
                getLayoutView()?.ivBg?.setImageResource(R.mipmap.bg_top_head)
            } else {
                getLayoutView()?.ivBg?.let {
                    GlideApp.with(this)
                            .load(data.message?.avatar)
                            .apply(RequestOptions.bitmapTransform(BlurTransformation(2, 10)))
                            .into(it)
                }
            }
        }
    }


    override fun close() {
        minePresenter.onDistroy()
    }
}