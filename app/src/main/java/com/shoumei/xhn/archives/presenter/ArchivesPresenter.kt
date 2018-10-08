package com.shoumei.xhn.archives.presenter

import com.shoumei.xhn.archives.data.ArchivesLoader
import com.shoumei.xhn.archives.data.ArchivesType
import com.shoumei.xhn.archives.view.IArchivesView
import com.shoumei.xhn.base.BaseModel
import com.shoumei.xhn.base.BasePresenter
import com.shoumei.xhn.base.IBaseView
import com.shoumei.xhn.http.ApiManager
import com.shoumei.xhn.login.manager.UserManager
import com.shoumei.xhn.mine.model.Profile
import com.shoumei.xhn.upload.data.UpLoader
import com.shoumei.xhn.utils.ToastUtil
import java.io.File

/**
 * 档案的Presenter
 * Created by zhangye on 2018/9/20.
 */
class ArchivesPresenter(private var iArchivesView: IArchivesView?) : BasePresenter<IBaseView>(iArchivesView) {

    var archivesLoader: ArchivesLoader? = null
    var upLoader: UpLoader? = null

    init {
        onCreate()
    }

    override fun onCreate() {
        archivesLoader = ArchivesLoader(composites)
        upLoader = UpLoader(composites)
    }

    //加载档案信息
    fun loadArchives(isLoadCache: Boolean) {
        iArchivesView?.showLoading()
        if (isLoadCache) {
            //加载缓存
            UserManager.getProfile()?.let {
                iArchivesView?.showArchiveData(it)
            }
        }
        archivesLoader?.loadArchives(object : ApiManager.OnResult<BaseModel<Profile>>() {
            override fun onSuccess(data: BaseModel<Profile>) {
                iArchivesView?.hideLoading()
                data.message?.let {
                    UserManager.setProfile(it) //本地存储
                    iArchivesView?.showArchiveData(it)
                }
            }

            override fun onFailed(code: String, message: String) {
                ToastUtil.show(message)
                iArchivesView?.hideLoading()
            }
        })
    }

    //修改档案资料
    fun modifyArchives(type: String, value: String) {
        iArchivesView?.showLoading()
        archivesLoader?.modifyArchives(type, value, object : ApiManager.OnResult<BaseModel<String>>() {
            override fun onSuccess(data: BaseModel<String>) {
                iArchivesView?.hideLoading()
                loadArchives(false)
            }

            override fun onFailed(code: String, message: String) {
                iArchivesView?.hideLoading()
                ToastUtil.show(message)
            }
        })
    }

    //上传头像并更新档案
    fun upDataAvatar(file:File){
        iArchivesView?.showLoading()
        upLoader?.updatePhoto(file,object :ApiManager.OnResult<BaseModel<ArrayList<String>>>(){
            override fun onSuccess(data: BaseModel<ArrayList<String>>) {
                iArchivesView?.hideLoading()
                data.message?.let {
                    modifyArchives(ArchivesType.AVATAR,it[0])
                }
            }

            override fun onFailed(code: String, message: String) {
                iArchivesView?.hideLoading()
                ToastUtil.show(message)
            }

        })
    }


    override fun onClose() {
        composites.dispose()
        iArchivesView = null
        archivesLoader = null
        upLoader = null
    }
}