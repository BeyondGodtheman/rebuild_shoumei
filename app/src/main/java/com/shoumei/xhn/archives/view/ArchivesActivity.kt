package com.shoumei.xhn.archives.view

import android.content.Intent
import android.view.View
import com.shoumei.xhn.R
import com.shoumei.xhn.archives.presenter.ArchivesPresenter
import com.shoumei.xhn.base.BaseActivity
import com.shoumei.xhn.mine.model.Profile
import com.shoumei.xhn.utils.CameraPhotoUtils
import com.shoumei.xhn.utils.GlideApp
import com.shoumei.xhn.utils.LoadingUtil
import com.shoumei.xhn.widget.dialog.PhotoDialog
import kotlinx.android.synthetic.main.activity_archive.*
import kotlinx.android.synthetic.main.activity_base.*
import java.io.File

/**
 * 档案页面
 * Created by zhangye on 2018/9/20.
 */
class ArchivesActivity : BaseActivity(), IArchivesView, PhotoDialog.OnItemClickListener {

    private val presenter: ArchivesPresenter by lazy {
        ArchivesPresenter(this)
    }

    private val loadingUtil: LoadingUtil by lazy {
        LoadingUtil(frameBody)
    }

    private val cameraUtils: CameraPhotoUtils by lazy {
        CameraPhotoUtils(this, object : CameraPhotoUtils.OnResultListener {
            override fun onResult(file: File) {
                presenter.upDataAvatar(file) //上传头像并刷新档案
            }
        })
    }

    override fun setLayout(): Int = R.layout.activity_archive

    override fun initView() {
        frameTitle.addView(titleManager.defaultTitle("档案"))
        ivHead.setOnClickListener(this)
    }

    override fun update() {

    }

    override fun loadData() {
        presenter.loadArchives(true) //网络请求加载
    }

    override fun showLoading() {
        loadingUtil.showLoading()
    }

    override fun hideLoading() {
        loadingUtil.hidenLoading()
    }

    //展示档案数据
    override fun showArchiveData(profile: Profile) {
        isvNickname.setValue(profile.nickname) //昵称
        isvName.setValue(profile.realname)
        isvBirthday.setValue(profile.birth)
        isvSex.setValue(profile.gender)
        isvAddress.setValue(profile.district)
        isvCompany.setValue(profile.company)
        isvWork.setValue(profile.industry_name)
        progressBar_big.progress = (profile.degree)
        progressBar_big.invalidate()
        GlideApp.with(this)
                .load(profile.avatar)
                .placeholder(R.drawable.defalut_updata_image)
                .into(ivHead)
    }

    override fun close() {
        presenter.onDestroy()
        cameraUtils.onDestroy()
    }

    override fun onClick(view: View) {
        when (view.id) {
            ivHead.id -> {
                val dialog = PhotoDialog(this)
                dialog.setOnItemClickListener(this)
                dialog.show()
            }
        }
    }

    //打开相机
    override fun onTopClick() {
        cameraUtils.startCamera()
    }

    //打开相册
    override fun onBottomClick() {
        cameraUtils.startPhoto()
    }

    //权限回调处理
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        cameraUtils.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    //相机回调处理
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        cameraUtils.onActivityResult(requestCode, resultCode, data)
    }

}