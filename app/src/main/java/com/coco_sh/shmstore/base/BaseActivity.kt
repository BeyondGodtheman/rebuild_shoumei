package com.coco_sh.shmstore.base

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.IBinder
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.coco_sh.shmstore.R
import com.coco_sh.shmstore.title.TitleManager
import kotlinx.android.synthetic.main.activity_base.*


@SuppressLint("Registered")

/**
 *
 * Created by zhangye on 2018/8/2.
 */
abstract class BaseActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var titleManager: TitleManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT //强制屏幕
        titleManager = TitleManager(this)
        setContentView(R.layout.activity_base)
        val bodyView = LayoutInflater.from(this).inflate(setLayout(), null, false)
        frameBody.addView(bodyView)
        initView()
        loadData()
    }

    abstract fun setLayout(): Int

    abstract fun initView()

    abstract fun update()


    abstract fun loadData()

    abstract fun close()


    override fun onTouchEvent(event: MotionEvent): Boolean {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        if (event.action == MotionEvent.ACTION_DOWN) {
            if (this.currentFocus != null) {
                if (this.currentFocus?.windowToken != null) {
                    imm.hideSoftInputFromWindow(this.currentFocus?.windowToken,
                            InputMethodManager.HIDE_NOT_ALWAYS)
                }
            }
        }
        return super.onTouchEvent(event)
    }


    /**
     * 隐藏输入法键盘
     */
    fun hideKeyboard() {
        val im = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        im.hideSoftInputFromWindow(this.currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }


    override fun onResume() {
        super.onResume()
        update()
    }


    override fun onDestroy() {
        super.onDestroy()
        close()
    }
}