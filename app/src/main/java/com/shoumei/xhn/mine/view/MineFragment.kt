package com.shoumei.xhn.mine.view

import android.content.Intent
import android.support.v4.content.ContextCompat
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.bumptech.glide.request.RequestOptions
import com.shoumei.xhn.R
import com.shoumei.xhn.archives.view.ArchivesActivity
import com.shoumei.xhn.base.BaseFragment
import com.shoumei.xhn.base.BaseModel
import com.shoumei.xhn.login.manager.UserManager
import com.shoumei.xhn.login.view.LoginActivity
import com.shoumei.xhn.mine.adapter.MineBottomNavAdapter
import com.shoumei.xhn.mine.adapter.MineTopNavAdapter
import com.shoumei.xhn.mine.model.CommonData
import com.shoumei.xhn.mine.model.MineNavEntity
import com.shoumei.xhn.mine.presenter.MinePresenter
import com.shoumei.xhn.title.TitleManager
import com.shoumei.xhn.utils.GlideApp
import com.shoumei.xhn.utils.RecycleViewDivider
import jp.wasabeef.glide.transformations.BlurTransformation
import kotlinx.android.synthetic.main.fragment_base.view.*
import kotlinx.android.synthetic.main.fragment_mine.view.*
import kotlinx.android.synthetic.main.layout_top_head.view.*


class MineFragment : BaseFragment(), IMineView {


    private val minePresenter: MinePresenter by lazy {
        MinePresenter(this)
    }


    override fun setLayout(): Int = R.layout.fragment_mine

    override fun initView() {

        immersionTitle() //title透明不占位

        getLayoutView()?.iframeTitle?.apply {
            visibility = View.VISIBLE
            addView(TitleManager(getBaseActivity()).mineTitle()) //加载title
        }
        getLayoutView()?.recycleTopNav?.setHasFixedSize(true)
        //分割线
        getLayoutView()?.recycleTopNav?.addItemDecoration(
                RecycleViewDivider(getBaseActivity(), LinearLayoutManager.VERTICAL, resources.getDimension(R.dimen.h2).toInt(),
                        ContextCompat.getColor(getBaseActivity(), R.color.grayE0))
        )

        getLayoutView()?.recycleBottomNav?.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        //分割线
        getLayoutView()?.recycleBottomNav?.addItemDecoration(
                RecycleViewDivider(getBaseActivity(), LinearLayoutManager.HORIZONTAL, resources.getDimension(R.dimen.h2).toInt(),
                        ContextCompat.getColor(getBaseActivity(), R.color.grayE0))
        )

        getLayoutView()?.ivHead?.setOnClickListener(this)

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

    override fun onClick(view: View) {
        when(view.id){
            getLayoutView()?.ivHead?.id -> {
                //跳转档案
                if (UserManager.isLogin()){
                    startActivity(Intent(context,ArchivesActivity::class.java))
                }else{
                    startActivity(Intent(context,LoginActivity::class.java))
                }
            }

        }
    }


    override fun topNavData(list: ArrayList<MineNavEntity>) {
        getLayoutView()?.recycleTopNav?.layoutManager = GridLayoutManager(context, list.size)
        getLayoutView()?.recycleTopNav?.adapter = MineTopNavAdapter(list)
    }

    override fun bottomNavData(list: ArrayList<MineNavEntity>) {
        getLayoutView()?.recycleBottomNav?.adapter = MineBottomNavAdapter(list)
    }


    //通用信息回调
    override fun onCommonData(data: CommonData) {
            UserManager.setCommon(data) //存储本地
            getLayoutView()?.tvName?.text = data.nickname
            getLayoutView()?.tvNo?.apply {
                text = (getString(R.string.no) + data.smno)
                visibility = View.VISIBLE
            }
            getLayoutView()?.ivHead?.let {
                GlideApp.with(this).load(data.avatar).placeholder(R.drawable.bg_update_head).into(it)
            }

            if (data.avatar.isNullOrEmpty()) {
                getLayoutView()?.ivBg?.setImageResource(R.mipmap.bg_top_head)
            } else {
                getLayoutView()?.ivBg?.let {
                    GlideApp.with(this)
                            .load(data.avatar)
                            .apply(RequestOptions.bitmapTransform(BlurTransformation(2, 10)))
                            .into(it)
                }
            }
    }


    override fun close() {
        minePresenter.onDestroy()
    }
}