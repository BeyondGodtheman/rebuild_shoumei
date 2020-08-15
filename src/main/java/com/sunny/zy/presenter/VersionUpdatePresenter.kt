package com.sunny.zy.presenter

import com.sunny.zy.contract.VersionUpdateContract
import com.sunny.zy.model.VersionUpdateModel
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch

/**
 * Desc
 * Author 张野
 * Mail zhangye98@foxmail.com
 * Date 2020/6/15 12:11
 */
class VersionUpdatePresenter(view: VersionUpdateContract.View) :
    VersionUpdateContract.Presenter(view) {

    private val versionUpdateModel: VersionUpdateModel by lazy {
        VersionUpdateModel()
    }

    //检查版本
    override fun checkVersion(version: Int) {
        launch(Main) {
            val versionBean = versionUpdateModel.checkVersion(version)
            if (versionBean != null) {
                if (versionBean.versionCode ?: 0 > version) {
                    view?.showVersionUpdate(versionBean)
                } else {
                    view?.noNewVersion()
                }
            }
        }
    }

    //下载新版本APK
    override fun downLoadAPk(url: String) {
        launch(Main) {
            val file = versionUpdateModel.downLoadAPK(url) {
                launch(Main) {
                    view?.progress(it)
                }
            }
            view?.downLoadResult(file?.path ?: "")
        }
    }


}