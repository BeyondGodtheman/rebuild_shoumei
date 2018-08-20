package com.coco_sh.shmstore.home.view

import android.support.v4.app.FragmentManager
import android.view.View
import android.view.ViewGroup
import com.coco_sh.shmstore.base.IBaseView

/**
 *
 * Created by zhangye on 2018/8/3.
 */
interface IMainView : IBaseView {

    fun getHomeLayout(): View

    fun getIncomeLayout(): View

    fun getShouMeiLayout(): View

    fun getMineLayout(): View

    fun getMenuGroup():ViewGroup

    fun getContentView():View

    fun getMainManager():FragmentManager
}