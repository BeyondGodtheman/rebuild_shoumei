package com.coco_sh.shmstore.base

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.coco_sh.shmstore.R
import kotlinx.android.synthetic.main.fragment_base.view.*

/**
 *
 * Created by zhangye on 2018/8/2.
 */
abstract class BaseFragment : Fragment(), View.OnClickListener {
    private var savedInstanceState: Bundle? = null
    private var mView: View? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        this.savedInstanceState = savedInstanceState
        mView = inflater.inflate(R.layout.fragment_base, container, false)
        mView?.iframeBody?.addView(inflater.inflate(setLayout(), container, false))
        initView()
        loadData()
        return mView
    }


    abstract fun setLayout(): Int

    abstract fun initView()

    abstract fun update()

    abstract fun loadData()

    fun getLayoutView(): View? = mView

    fun getBaseActivity(): BaseActivity = activity as BaseActivity

    override fun onDestroy() {
        super.onDestroy()
    }
}