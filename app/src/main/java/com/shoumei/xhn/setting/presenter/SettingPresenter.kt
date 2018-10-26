package com.shoumei.xhn.setting.presenter

import com.shoumei.xhn.R
import com.shoumei.xhn.SmApplication
import com.shoumei.xhn.base.BaseModel
import com.shoumei.xhn.base.BasePresenter
import com.shoumei.xhn.http.ApiManager
import com.shoumei.xhn.login.manager.UserManager
import com.shoumei.xhn.mine.data.MineLoader
import com.shoumei.xhn.mine.model.CommonData
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
    private var mineLoader:MineLoader? = null

    override fun onCreate() {
        fileUtils = FileUtils()
        mineLoader = MineLoader(composites)
    }

    override fun onClose() {
        fileUtils = null
        mineLoader = null
        iSettingView = null
    }

    //获取缓存大小
    fun getCacheSize() {
        var size = 0L
        showLoading()
        //创建协程
        val countJob = async {
            size += fileUtils?.getCacheSize() ?: 0
        }

        launch(UI) {
            //启动协程并挂起，等待执行结果
            countJob.await()
            hideLoading()
            fileUtils?.formatFileSize(size)?.let {
                iSettingView?.showCacheSize(it)
            }
        }
    }

    fun loadCommonData(){
        showLoading()
        mineLoader?.loadCommonData(object :ApiManager.OnResult<BaseModel<CommonData>>(){
            override fun onSuccess(data: BaseModel<CommonData>) {
                hideLoading()
                data.message?.let {
                    UserManager.setCommon(it)
                    iSettingView?.loadCommonData(it)
                }
            }

            override fun onFailed(code: String, message: String) {
                hideLoading()
                showMessage(message)
            }

        })
    }

    //清理缓存
    fun clearCache() {
        showLoading()
        //创建协程
        val deleteJob = async {
            fileUtils?.deleteAllFile()
        }
        launch(UI) {
            //启动协程并挂起，等待执行结果
            deleteJob.await()
            GlideApp.get(SmApplication.getApp()).clearMemory()
            hideLoading()
            showMessage(SmApplication.getApp().getString(R.string.clearComplete))
            getCacheSize()
        }
    }
}