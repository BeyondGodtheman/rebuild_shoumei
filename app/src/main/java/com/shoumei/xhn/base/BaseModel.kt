package com.shoumei.xhn.base

/**
 * 公共实体类
 * Created by 张野 on 2017/9/14.
 */

open class BaseModel<T> {
    var msg: String = "success"
    var code: String = "0"
    var data: T? = null

    override fun toString(): String {
        return "BaseModel(msg='$msg', code='$code', data=$data)"
    }

}