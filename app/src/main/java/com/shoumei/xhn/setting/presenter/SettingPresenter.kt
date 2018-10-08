package com.shoumei.xhn.setting.presenter

import com.shoumei.xhn.R
import com.shoumei.xhn.SmApplication
import com.shoumei.xhn.base.BasePresenter
import com.shoumei.xhn.setting.view.ISettingView
import com.shoumei.xhn.utils.FileUtils
import com.shoumei.xhn.utils.GlideApp
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch

/**
 * 设置页面的Presenter
 * Created by zhangye on 2018/9/18.
 */
class SettingPresenter(private var iSettingView: ISettingView?) : BasePresenter<ISettingView>(iSettingView) {

    private var fileUtils: FileUtils? = null

    override fun onCreate() {
        fileUtils = FileUtils()
    }

    override fun onClose() {
        fileUtils = null
        iSettingView = null
    }

    //获取缓存大小
    fun getCacheSize() {
        var size = 0L
        iSettingView?.showLoading()
        //创建协程
        val countJob = async {
            size += fileUtils?.getCacheSize() ?: 0
        }

        launch(UI) {
            //启动协程并挂起，等待执行结果
            countJob.await()
            iSettingView?.hideLoading()
            fileUtils?.formatFileSize(size)?.let {
                iSettingView?.showCacheSize(it)
            }
        }
    }

    //清理缓存
    fun clearCache() {
        iSettingView?.showLoading()
        //创建协程
        val deleteJob = async {
            fileUtils?.deleteAllFile()
        }
        launch(UI) {
            //启动协程并挂起，等待执行结果
            deleteJob.await()
            GlideApp.get(SmApplication.getApp()).clearMemory()
            iSettingView?.hideLoading()
            iSettingView?.showMessage(SmApplication.getApp().getString(R.string.clearComplete))
            getCacheSize()
        }
    }
}