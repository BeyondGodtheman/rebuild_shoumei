package com.shoumei.xhn.main

import android.view.View
import com.shoumei.xhn.R
import com.shoumei.xhn.base.BaseActivity
import com.shoumei.xhn.http.ZyHttp
import com.shoumei.xhn.utils.LogUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import org.json.JSONObject

class MainActivity : BaseActivity() {

    override fun setLayout(): Int = R.layout.activity_main

    override fun initView() {

        btn_loading.setOnClickListener {

        }

        setOnClickListener(btn_loading)
        titleManager.defaultTitle(getFrameTitle(), "我是点击事件")

    }


    override fun loadData() {

    }

    override fun onClickEvent(view: View) {
        when (view.id) {
            btn_loading.id -> {

                MainScope().launch(Main) {

                    val json = JSONObject()
                    json.put("username", "13126596191")
                    json.put("password", "121212")
                    json.put("savepassword", "on")

                    val result = ZyHttp.postJson("http://www.110zhuangbei.com/app/sys/login", json.toString(), UserInfo::class.java)
                    if (result.isSuccess()){
                        LogUtil.i("请求结果：${result.bean}")
                    }

                }
            }
        }
    }

}
