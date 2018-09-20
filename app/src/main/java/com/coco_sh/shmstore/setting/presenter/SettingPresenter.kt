package com.coco_sh.shmstore.setting.presenter

import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.SmApplication
import com.coco_sh.shmstore.base.BasePresenter
import com.coco_sh.shmstore.setting.view.ISettingView
import com.coco_sh.shmstore.utils.FileUtlis
import com.coco_sh.shmstore.utils.GlideApp
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * 设置页面的Presenter
 * Created by zhangye on 2018/9/18.
 */
class SettingPresenter(private var iSettingView: ISettingView?) : BasePresenter<ISettingView>(iSettingView) {

    private var fileUtlis: FileUtlis? = null

    override fun onCreate() {
        fileUtlis = FileUtlis()
    }

    override fun onDistroy() {
        fileUtlis = null
        iSettingView = null
    }

    //获取缓存大小
    fun getCacheSize() {
        var size = 0L
        iSettingView?.showLoading()
        //创建协程
        val countJob = async {
            size += fileUtlis?.getCacheSize() ?: 0
        }

        launch(UI) {
            //启动协程并挂起，等待执行结果
            countJob.await()
            iSettingView?.hidenLoading()
            fileUtlis?.formetFileSize(size)?.let {
                iSettingView?.showCacheSize(it)
            }
        }
    }

    //清理缓存
    fun clearCache() {
        iSettingView?.showLoading()
        //创建协程
        val deleteJob = async {
            fileUtlis?.deleteAllFile()
        }
        launch(UI) {
            //启动协程并挂起，等待执行结果
            deleteJob.await()
            GlideApp.get(SmApplication.getApp()).clearMemory()
            iSettingView?.hidenLoading()
            iSettingView?.showMessage(SmApplication.getApp().getString(R.string.clearComplete))
            getCacheSize()
        }
    }
}