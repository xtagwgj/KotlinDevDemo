package com.xtagwgj.kotlindevdemo

import android.os.Bundle
import android.view.KeyEvent
import com.xtagwgj.baseproject.base.AppManager
import com.xtagwgj.baseproject.utils.LogUtils
import com.xtagwgj.baseproject.utils.ToastUtils
import com.xtagwgj.kdev.base.BaseActivity
import com.xtagwgj.kdev.extensions.Preference
import com.xtagwgj.kdev.extensions.isNumber
import com.xtagwgj.kdev.extensions.showOrHidePwd
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var token: String by Preference(this, "Key", "")

    val city: String by lazy {
        ""
    }

    override fun initEventListener() {

        clickEvent(button, {
            val flag = passwordText.showOrHidePwd()
            LogUtils.e("MainActivity", "$flag ${passwordText.isNumber()}")
        })

    }

    override fun initView(p0: Bundle?) {
    }

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            closeLoadingDialog()
            return isConsumeBackKey()
        }
        return super.onKeyDown(keyCode, event)
    }

    private var exitTime: Long = 0

    private fun isConsumeBackKey(): Boolean {
        val activity = AppManager.getAppManager().currentActivity()
        if (activity is MainActivity) {
            if (System.currentTimeMillis() - exitTime > 2000) {//未处理监听事件，请求后续监听
                ToastUtils.showShortToast(this, "再按一次退出程序")
                exitTime = System.currentTimeMillis()
            } else {
                AppManager.getAppManager().finishActivity()
                android.os.Process.killProcess(android.os.Process.myPid())
                System.exit(0)
            }

        } else {
            exitTime = 0
            AppManager.getAppManager().finishActivity()
            if (AppManager.getAppManager().activityStackSize() < 1) {
                android.os.Process.killProcess(android.os.Process.myPid())
                System.exit(0)
            }
        }
        return true
    }
}
