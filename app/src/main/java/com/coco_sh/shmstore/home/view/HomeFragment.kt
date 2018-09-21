package com.coco_sh.shmstore.home.view

import android.view.View
import android.widget.TextView
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.base.BaseActivity
import com.coco_sh.shmstore.base.BaseFragment
import com.coco_sh.shmstore.home.presenter.HomePresenter
import com.coco_sh.shmstore.utils.IntentCode
import com.coco_sh.shmstore.utils.PermissionUtil
import kotlinx.android.synthetic.main.fragment_base.view.*
import kotlinx.android.synthetic.main.fragment_home.view.*
import kotlinx.android.synthetic.main.layout_home_title.*

/**
 *首页Framgnet
 */
class HomeFragment : BaseFragment(), IHomeView {
    override fun close() {
    }

    private var permissionUtil: PermissionUtil? = null

    private var homePresenter = HomePresenter(this)

    override fun getCityView(): TextView = tvCity

    private var titleView: View? = null


    override fun showLoading() {
    }

    override fun hideLoading() {
    }

    override fun setLayout(): Int = R.layout.fragment_home

    override fun initView() {
        titleView = View.inflate(context, R.layout.layout_home_title, null)
        getLayoutView()?.iframeTitle?.visibility = View.VISIBLE
        getLayoutView()?.iframeTitle?.addView(titleView)
        permissionUtil = PermissionUtil(activity as BaseActivity)
    }

    override fun update() {
    }

    override fun loadData() {
        if (permissionUtil?.locationPermission() == true) {
            homePresenter.startLocation() //启动定位
        }
        fragmentManager?.let {
            getLayoutView()?.homeBottomView?.setFragmentManager(it)

        }
    }


    override fun onClick(p0: View?) {
    }

//    private var homeTitleFragment: HomeTitleFragment? = null
//
//    override fun setLayout(): Int = R.layout.fragment_home
//
//    override fun reTryGetData() {
//
//    }
//
//    override fun initView() {
//        homeTitleFragment = getTitleManager().homeTitle() as HomeTitleFragment
//        homeTitleFragment?.let {
//            it.homeFragment = this
//            showTitle(it)
//        }
//
//        getLayoutView().layoutOne.setOnClickListener(this)
//        getLayoutView().layoutTwo.setOnClickListener(this)
//        getLayoutView().layoutThree.setOnClickListener(this)
//        getLayoutView().homeBottomView.setFragmentManager(childFragmentManager)
//        loadBanner()
//    }
//
//    override fun onListener(view: View) {
//        when (view.id) {
//            R.id.layoutOne -> {
//                startBonus("大众红包")
//            }
//
//            R.id.layoutTwo -> {
//                startBonus("精准红包")
////                startBonus("媒体扶贫")
//
//            }
//
//            R.id.layoutThree -> {
////                startBonus("媒体扶贫")
//                startBonus("粉丝红包")
////                startBonus("消费扶贫")
//            }
//        }
//    }
//
//    override fun close() {
//    }
//
//
//    /**
//     * 认证引导弹窗
//     */
////    private fun autEnt() {
////        val info = UserManager.getLogin()?.resResIndexInfo
////        if (info != null) {
////            val mDialog = CertificationDialog(activity)
////            if (info.authStatus == "H") {
////                //新媒人
////                mDialog.setDesc("您接受了<br>${info.realName}<br>发来的<font color='#D8253B'>新媒人认证</font>邀请")
////            } else {
////                //服务商
////                mDialog.setDesc("您接受了<br>${info.corpFname}<br>发来的<font color='#D8253B'>企业主认证</font>邀请")
////            }
////            mDialog.show()
////
////            mDialog.OnClickListener = View.OnClickListener {
////                if (info.authStatus == "H") {
////                    //新媒人
////                    PartnerSplashActivity.start(activity)
////                } else {
////                    //服务商
////                    EnterpriseCertificationActivity.start(activity)
////                }
////            }
////        }
////    }
//
//
//    //跳转红包页面
//    private fun startBonus(title: String) {
//        val it = Intent(context, BonusListActivity::class.java)
//        it.putExtra("title", title)
//        startActivity(it)
//    }
//
//    fun loadBanner() {
//        val params = hashMapOf<String, String>()
//        ApiManager.get(0, activity as BaseActivity, params, Constant.HOME_BANNER, object : ApiManager.OnResult<BaseModel<Banner>>() {
//            override fun onSuccess(data: BaseModel<Banner>) {
//                if (data.success) {
//                    data.entity?.let {
//                        getLayoutView().homeAdView.loadData(it.resHomePageBannersVoList?: arrayListOf())
//                        UserManager.loadMemberEntrance(getBaseActivity()) //加载用户资料
//                    }
//                }
//            }
//
//            override fun onFailed(e: Throwable) {
//            }
//
//            override fun onCatch(data: BaseModel<Banner>) {
//            }
//
//        })
//    }
//
//    override fun onPause() {
//        super.onPause()
//        getLayoutView().homeAdView.stopLoop()
//    }
//
//    override fun onStart() {
//        super.onStart()
//        getLayoutView().homeAdView.startLooep()
//        SmApplication.getApp().removeData(DataCode.BONUS)
//    }
//
//
//    override fun onHiddenChanged(hidden: Boolean) {
//        super.onHiddenChanged(hidden)
//        homeTitleFragment?.onHiddenChanged(hidden)
//        if (!hidden) {
//            loadBanner()
//            SmApplication.getApp().getData<Location>(DataCode.LOCATION, false)?.let {
//                getLayoutView().homeBottomView.loadData(it)
//            }
//        }
//    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == IntentCode.LOCATION) {
            if (permissionUtil?.checkPermission(permissions) == true) {
                homePresenter.startLocation() //启动定位
            }
        }
    }
}
