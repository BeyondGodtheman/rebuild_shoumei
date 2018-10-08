package com.shoumei.xhn.home.view

import android.content.Intent
import android.view.View
import android.view.ViewGroup
import com.shoumei.xhn.R
import com.shoumei.xhn.base.BaseActivity
import com.shoumei.xhn.base.BaseFragment
import com.shoumei.xhn.income.IncomeFragment
import com.shoumei.xhn.login.manager.UserManager
import com.shoumei.xhn.login.view.LoginActivity
import com.shoumei.xhn.message.MessageFragment
import com.shoumei.xhn.mine.view.MineFragment
import com.shoumei.xhn.shoumei.ShouMeiFragment
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.include_menu.*

class MainActivity : BaseActivity() {
    private var lastPostion = 0
    private val fragments = ArrayList<BaseFragment>()

    override fun setLayout(): Int = R.layout.activity_main
    override fun initView() {

        frameTitle.visibility = View.GONE

        //加粗4个导航的图标
        iv_home.boldText()
        iv_income.boldText()
        iv_message.boldText()
        iv_mine.boldText()

        rlHome.setOnClickListener(this)
        rlInCome.setOnClickListener(this)
        rlShoumei.setOnClickListener(this)
        rlMessage.setOnClickListener(this)
        rlMine.setOnClickListener(this)

        fragments.add(HomeFragment()) //首页
        fragments.add(IncomeFragment()) //收益
        fragments.add(ShouMeiFragment()) //首媒之家
        fragments.add(MessageFragment()) //消息页面
        fragments.add(MineFragment()) //我的

        selectMenu(0) //默认展示页面
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
                    startActivity(Intent(this, LoginActivity::class.java))
                    return
                }
                selectMenu(1)
            }

            R.id.rlShoumei -> {
                if (!UserManager.isLogin()) {
                    startActivity(Intent(this, LoginActivity::class.java))
                    return
                }
                selectMenu(2)
            }

            R.id.rlMessage -> {
                if (!UserManager.isLogin()) {
                    startActivity(Intent(this, LoginActivity::class.java))
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
        updateMenu(index)
        supportFragmentManager.beginTransaction().hide(fragments[lastPostion]).commit()
        if (supportFragmentManager.findFragmentByTag(fragments[index].javaClass.simpleName) == null) {
            supportFragmentManager.beginTransaction().add(frameContent.id, fragments[index], fragments[index].javaClass.simpleName).commit()
        } else {
            supportFragmentManager.beginTransaction().show(fragments[index]).commit()
        }

        lastPostion = index

    }

    //更新底部菜单样式
    private fun updateMenu(index: Int) {
        for (i in 0 until (llgroup as ViewGroup).childCount) {
            (llgroup as ViewGroup).getChildAt(i).isSelected = (i == index)
        }
    }


    override fun close() {

    }
}
