package com.shoumei.xhn.forget.view

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.shoumei.xhn.R
import com.shoumei.xhn.base.BaseActivity
import com.shoumei.xhn.widget.dialog.SmediaDialog
import com.shoumei.xhn.forget.presenter.ForgetPassPresenter
import com.shoumei.xhn.http.Constant
import com.shoumei.xhn.utils.IntentCode
import com.shoumei.xhn.utils.PermissionUtil
import com.shoumei.xhn.widget.button.TimerButton
import kotlinx.android.synthetic.main.activity_base.*
import kotlinx.android.synthetic.main.activity_forget_pass.*

/**
 * 忘记密码页面
 * Created by zhangye on 2018/8/3.
 */
class ForgetPassActivity : BaseActivity(), IForgetPassView {
    override fun close() {
        forgetPassPresenter.onDestroy()
    }

    private var permissionUtil: PermissionUtil? = null
    private var forgetPassPresenter = ForgetPassPresenter(this)

    override fun showLoading() {

    }

    override fun setLayout(): Int = R.layout.activity_forget_pass

    override fun initView() {

        permissionUtil = PermissionUtil(this)

        frameTitle.addView(titleManager.defaultTitle("忘记密码"))
        tbSend.setCallListener(View.OnClickListener {
            forgetPassPresenter.sendMSM() //发送短信验证码
        })

        tvProblem.setOnClickListener(this)
        btNext.setOnClickListener(this)
    }

    override fun update() {
    }

    override fun loadData() {
    }


    override fun onClick(view: View) {
        when (view.id) {
            btNext.id -> {
                //确认密码弹窗
                val smDialog = SmediaDialog(this)
                smDialog.showSmsMotifyPassword(editPass.text.toString(), View.OnClickListener {
                    forgetPassPresenter.forgetPass()
                })
            }

            tvProblem.id -> {
                val smDialog = SmediaDialog(this)
                smDialog.showCallPhone(View.OnClickListener {
                    if (permissionUtil?.callPermission() == true) {
                        callPhone()
                    }
                })
            }
        }
    }


    override fun hideLoading() {
    }

    override fun getEditPhone(): EditText = editPhone

    override fun getEditCode(): EditText = etIdentifyCode

    override fun getEditPass(): EditText = editPass

    override fun getBtnSend(): TimerButton = tbSend

    override fun getBtnNext(): Button = btNext


    override fun sendSMSResult() {
        tbSend.action()  //按钮开始倒计时
    }

    override fun forgetResult() {
        finish()
    }


    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == IntentCode.PHONE) {
            if (permissionUtil?.checkPermission(permissions) == true) {
                callPhone()
            }
        }
    }

    @SuppressLint("MissingPermission")
    private fun callPhone() {
        val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:${Constant.SMEDIA_PHONE}"))
        startActivity(intent)
    }
}