package com.coco_sh.shmstore.mine.data

import com.coco_sh.shmstore.base.BaseModel
import com.coco_sh.shmstore.http.ApiManager
import com.coco_sh.shmstore.http.Constant
import com.coco_sh.shmstore.mine.model.CommonData
import io.reactivex.disposables.CompositeDisposable

/**
 * 我的页面数据加载
 * Created by zhangye on 2018/8/28.
 */
class MineLoader(private var composite: CompositeDisposable) {

    //加载APP通用数据（含认证状态）
    fun loadCommonData(onResult: ApiManager.OnResult<BaseModel<CommonData>>) {
        ApiManager.get(composite, null, Constant.COMMON_DATA, onResult)
    }
}