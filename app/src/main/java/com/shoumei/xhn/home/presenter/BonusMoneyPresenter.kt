package com.shoumei.xhn.home.presenter

import com.shoumei.xhn.base.BasePresenter
import com.shoumei.xhn.home.view.IBonusMoneyView

/**
 *
 * Created by zhangye on 2018/8/3.
 */
class BonusMoneyPresenter(private var iBonusMoneyView: IBonusMoneyView) : BasePresenter<IBonusMoneyView>(iBonusMoneyView) {
    override fun onClose() {
    }

    override fun onCreate() {

    }
}