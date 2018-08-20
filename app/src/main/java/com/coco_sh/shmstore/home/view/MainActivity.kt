package com.coco_sh.shmstore.home.view

import android.support.v4.app.FragmentManager
import android.view.View
import android.view.ViewGroup
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.base.BaseActivity
import com.coco_sh.shmstore.dialog.SmediaDialog
import com.coco_sh.shmstore.home.presenter.MainPresenter
import com.coco_sh.shmstore.login.manager.UserManager
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_menu.*

class MainActivity : BaseActivity(), IMainView {

    private var mainPresenter = MainPresenter(this)

    override fun setLayout(): Int = R.layout.activity_main
    override fun initView() {

        mainPresenter.onCreate()

        frameTitle.visibility = View.GONE
        rlHome.setOnClickListener(this)
        rlInCome.setOnClickListener(this)
        rlShoumei.setOnClickListener(this)
        rlMessage.setOnClickListener(this)
        rlMine.setOnClickListener(this)
    }

    override fun update() {
    }

    @Suppress("UNCHECKED_CAST")
    override fun loadData() {

    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.rlHome -> {
                mainPresenter.selectTab(0)
            }
            R.id.rlInCome -> {
                if (!UserManager.isLogin()) {
                    SmediaDialog(this).showLogin()
                    return
                }
                mainPresenter.selectTab(1)
            }

            R.id.rlShoumei -> {
                if (!UserManager.isLogin()) {
                    SmediaDialog(this).showLogin()
                    return
                }
                mainPresenter.selectTab(2)
            }

            R.id.rlMessage -> {
                if (!UserManager.isLogin()) {
                    SmediaDialog(this).showLogin()
                    return
                }
                mainPresenter.selectTab(3)
            }
            R.id.rlMine -> {
                mainPresenter.selectTab(4)
            }

        }
    }

    override fun getMenuGroup(): ViewGroup = llgroup

    override fun getHomeLayout(): View = rlHome

    override fun getIncomeLayout(): View = rlInCome

    override fun getShouMeiLayout(): View = rlShoumei

    override fun getMineLayout(): View = rlMine

    override fun getContentView(): View = flBottomTabFragmentContainer

    override fun getMainManager(): FragmentManager = supportFragmentManager

    override fun showLoading() {
    }

    override fun hidenLoading() {
    }

    override fun close() {

    }
}
