package com.shoumei.xhn.archives.data

import com.shoumei.xhn.base.BaseModel
import com.shoumei.xhn.http.ApiManager
import com.shoumei.xhn.http.Constant
import com.shoumei.xhn.mine.model.Profile
import io.reactivex.disposables.CompositeDisposable

/**
 * 档案数据加载
 * Created by zhangye on 2018/9/20.
 */
class ArchivesLoader(private var composites: CompositeDisposable) {

    /**
     * 加载用户资料
     */
    fun loadArchives(onResult: ApiManager.OnResult<BaseModel<Profile>>) {
        ApiManager.get(composites, null, Constant.PROFILE, onResult)
    }

    /**
     * 修改用户资料
     * 性别和地址单独处理数据
     */
    fun modifyArchives(type: String, value: String, onResult: ApiManager.OnResult<BaseModel<String>>) {
        var mValue = value
        val params = hashMapOf<String, String>()

        //性别
        if (type == ArchivesType.GENDER) {
            mValue = if (value == "女") "0" else "1"
        }

        //地址
        if (type == ArchivesType.ADDRESS) {
            val addressCode = mValue.split("-")
            params[ArchivesType.PROVINCE] = addressCode[0]
            params[ArchivesType.CITY] = addressCode[1]
            params[ArchivesType.TOWN] = addressCode[2]
        } else {
            params[type] = mValue
        }

        ApiManager.post(composites, params, Constant.PROFILE_UPDATE, onResult)
    }
}