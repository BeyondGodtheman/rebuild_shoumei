package com.coco_sh.shmstore.home.presenter

import android.support.v4.app.Fragment
import android.widget.LinearLayout
import com.coco_sh.shmstore.base.BasePresenter
import com.coco_sh.shmstore.base.IBaseView
import com.coco_sh.shmstore.home.view.HomeFragment
import com.coco_sh.shmstore.home.view.IMainView

/**
 *
 * Created by zhangye on 2018/8/3.
 */
class MainPresenter(private var mainView: IMainView) : BasePresenter<IBaseView>(mainView) {

    private var position = 0
    private var currentPage = -1
    private val fragments = arrayListOf<Fragment>()

    override fun onCreate() {
        //初始化所有页面
        val homeFragment = HomeFragment()
        fragments.add(homeFragment)

//        val talkFragment = IncomeFragment()
//        fragments.add(talkFragment)
//
//        val shoumeiFragment = ShoumeiFragment()
//        fragments.add(shoumeiFragment)
//
//        val messageFragment = MessageFragment()
//        fragments.add(messageFragment)
//
//        mineFragment = MineFragment()
//        fragments.add(mineFragment!!)
        selectTab(0)
//        getMsgCount()
    }

    override fun onDistroy() {

    }


    fun selectTab(index: Int) {

        position = index

        updateMenu(index)
        if (currentPage != -1) {
            mainView.getMainManager().beginTransaction().hide(fragments[currentPage]).commit()
        }

        if (mainView.getMainManager().findFragmentByTag(fragments[index].javaClass.simpleName) == null) {
            mainView.getMainManager().beginTransaction().add(mainView.getContentView().id, fragments[index], fragments[index].javaClass.simpleName).commit()
        } else {
            mainView.getMainManager().beginTransaction().show(fragments[index]).commit()
        }

        currentPage = index

    }

    private fun updateMenu(index: Int) {
        for (i in 0 until (mainView.getMenuGroup() as LinearLayout).childCount) {
            (mainView.getMenuGroup() as LinearLayout).getChildAt(i).isSelected = i == index
        }
    }
}