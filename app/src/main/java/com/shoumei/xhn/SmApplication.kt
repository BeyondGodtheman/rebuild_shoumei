package com.shoumei.xhn

import android.annotation.SuppressLint
import android.graphics.Typeface
import android.provider.Settings
import android.support.multidex.MultiDexApplication
import com.shoumei.xhn.utils.DigestUtils
import java.util.*

/**
 * 应用类
 * Created by zhangye on 2018/8/2.
 */
class SmApplication : MultiDexApplication() {
    private val storeMap = HashMap<String, Any>() //内存数据存储
    lateinit var iconFontType: Typeface  //字体图库

    /**
     * 单例
     */
    companion object {
        private lateinit var instance: SmApplication
        fun getApp(): SmApplication = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        iconFontType = Typeface.createFromAsset(assets, "font/iconfont.ttf") //加载字体库文件

    }

    @SuppressLint("HardwareIds")
    fun getDeviceID(): String {
        return DigestUtils.md5(Settings.Secure.getString(contentResolver, Settings.Secure.ANDROID_ID))
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> getData(key: String, isDelete: Boolean): T? {

        if (!storeMap.containsKey(key)) {
            return null
        }

        val result = storeMap[key]

        if (isDelete) {
            removeData(key)
        }
        return result as T
    }


    /**
     * 存储数据
     */
    fun setData(key: String, t: Any?) {
        if (t != null) {
            storeMap[key] = t
        }
    }

    /**
     * 删除数据
     */
    fun removeData(key: String) {
        storeMap.remove(key)
    }
}