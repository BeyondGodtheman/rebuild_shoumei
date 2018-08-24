package com.coco_sh.shmstore.home.view

import android.view.View
import android.view.ViewGroup
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.base.BaseActivity
import com.coco_sh.shmstore.base.BaseFragment
import com.coco_sh.shmstore.dialog.SmediaDialog
import com.coco_sh.shmstore.login.manager.UserManager
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_menu.*

class MainActivity : BaseActivity() {
    private var position = 0
    private var lastPostion = -1
    private val fragments = ArrayList<BaseFragment>()

    override fun setLayout(): Int = R.layout.activity_main
    override fun initView() {

        frameTitle.visibility = View.GONE
        rlHome.setOnClickListener(this)
        rlInCome.setOnClickListener(this)
        rlShoumei.setOnClickListener(this)
        rlMessage.setOnClickListener(this)
        rlMine.setOnClickListener(this)

        val homeFragment = HomeFragment()
        fragments.add(homeFragment)
        selectMenu(0)

    }

    override fun update() {
    }

    override fun loadData() {

    }


    override fun onClick(view: View) {
        when (view.id) {
            R.id.rlHome -> {
                selectMenu(0)
            }
            R.id.rlInCome -> {
                if (!UserManager.isLogin()) {
                    SmediaDialog(this).showLogin()
                    return
                }
                selectMenu(1)
            }

            R.id.rlShoumei -> {
                if (!UserManager.isLogin()) {
                    SmediaDialog(this).showLogin()
                    return
                }
                selectMenu(2)
            }

            R.id.rlMessage -> {
                if (!UserManager.isLogin()) {
                    SmediaDialog(this).showLogin()
                    return
                }
                selectMenu(3)
            }
            R.id.rlMine -> {
                selectMenu(4)
            }

        }
    }


    //切换底部菜单
    private fun selectMenu(index: Int) {
        position = index

        updateMenu(index)
        if (lastPostion != -1) {
            supportFragmentManager.beginTransaction().hide(fragments[lastPostion]).commit()
        }

        if (supportFragmentManager.findFragmentByTag(fragments[index].javaClass.simpleName) == null) {
            supportFragmentManager.beginTransaction().add(frameContent.id, fragments[index], fragments[index].javaClass.simpleName).commit()
        } else {
            supportFragmentManager.beginTransaction().show(fragments[index]).commit()
        }

        lastPostion = index

    }

    //更新底部菜单样式
    private fun updateMenu(index: Int) {
        for (i in 0 until (llParent as ViewGroup).childCount) {
            (llParent as ViewGroup).getChildAt(i).isSelected = i == index
        }
    }


    override fun close() {

    }
}
