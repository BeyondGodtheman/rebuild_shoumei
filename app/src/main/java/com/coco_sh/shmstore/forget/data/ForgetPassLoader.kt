package com.coco_sh.shmstore.forget.data

import com.coco_sh.shmstore.base.BaseModel
import com.coco_sh.shmstore.http.ApiManager
import com.coco_sh.shmstore.http.Constant
import com.coco_sh.shmstore.utils.DigestUtils
import io.reactivex.disposables.CompositeDisposable

/**
 * 忘记密码网络请求
 * Created by zhangye on 2018/8/3.
 */
class ForgetPassLoader(private var composites: CompositeDisposable) {

    //忘记密码
    fun forgetPass(phone: String, password: String, smscode: String, smskey: String, onResult: ApiManager.OnResult<BaseModel<String>>) {
            val map = HashMap<String, String>()
            map["phone"] = phone    //手机号 (必填)
            map["passwd"] = DigestUtils.md5(password) // 密码 (必填, 密文, 加密方式: md5[明文])
            map["smscode"] = smscode //短信验证码 (必填, 由短信发送接口反馈给客户端)
            map["smskey"] = smskey
            ApiManager.post(composites, map, Constant.FORGOTPWD, onResult)
    }

}