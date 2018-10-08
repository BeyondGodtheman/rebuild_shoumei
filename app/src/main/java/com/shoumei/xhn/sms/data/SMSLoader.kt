package com.shoumei.xhn.sms.data

import com.shoumei.xhn.base.BaseModel
import com.shoumei.xhn.http.ApiManager
import com.shoumei.xhn.http.Constant
import com.shoumei.xhn.sms.model.SmSMS
import com.shoumei.xhn.sms.type.SmsType
import io.reactivex.disposables.CompositeDisposable

/**
 * 发送验证码网络处理类
 * Created by zhangye on 2018/8/2.
 */
class SMSLoader(private var composites: CompositeDisposable) {
    /**
     *   发送验证码
     */
    fun sendCode(phone: String, type: SmsType, oresult: ApiManager.OnResult<BaseModel<SmSMS>>) {
        val map = HashMap<String, String>()
        map["phone"] = phone
        map["behavior"] = type.value
        ApiManager.post(composites, map, Constant.REGISTER_SEND_CODE, oresult)
    }
}