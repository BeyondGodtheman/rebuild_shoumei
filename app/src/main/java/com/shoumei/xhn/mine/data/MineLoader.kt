package com.shoumei.xhn.mine.data

import com.shoumei.xhn.base.BaseModel
import com.shoumei.xhn.http.ApiManager
import com.shoumei.xhn.http.Constant
import com.shoumei.xhn.mine.model.CommonData
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