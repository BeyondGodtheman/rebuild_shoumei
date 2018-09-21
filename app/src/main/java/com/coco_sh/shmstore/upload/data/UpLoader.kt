package com.coco_sh.shmstore.upload.data

import com.coco_sh.shmstore.base.BaseModel
import com.coco_sh.shmstore.http.ApiManager
import io.reactivex.disposables.CompositeDisposable
import java.io.File

/**
 * 上传Model
 * Created by zhangye on 2018/9/21.
 */
class UpLoader(private var composites: CompositeDisposable) {

    //上传图片
    fun updatePhoto(file: File, onResult: ApiManager.OnResult<BaseModel<ArrayList<String>>>) {
        ApiManager.postImage(composites, file.path, onResult)
    }
}