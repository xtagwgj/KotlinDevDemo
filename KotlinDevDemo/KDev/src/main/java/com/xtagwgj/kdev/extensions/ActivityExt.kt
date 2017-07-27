package com.xtagwgj.kdev.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.jakewharton.rxbinding2.view.RxView
import com.trello.rxlifecycle2.components.RxActivity
import com.xtagwgj.baseproject.constant.BaseConstants
import com.xtagwgj.baseproject.utils.LogUtils

/**
 * activity
 * Created by xtagwgj on 2017/7/26.
 */
fun RxActivity.clickEvent(view: View, condition: () -> Boolean, task: () -> Unit) {
    RxView.clicks(view)
            .throttleFirst(BaseConstants.THROTTLE_TIME.toLong(), java.util.concurrent.TimeUnit.MILLISECONDS)
            .compose(bindToLifecycle())
            .filter { condition() }
            .subscribe(
                    { task() },
                    { LogUtils.e("BaseActivity", it.message) }
            )
}

fun RxActivity.clickEvent(view: View, task: () -> Unit) {
    RxView.clicks(view)
            .throttleFirst(BaseConstants.THROTTLE_TIME.toLong(), java.util.concurrent.TimeUnit.MILLISECONDS)
            .subscribe(
                    { task() },
                    { LogUtils.e("BaseActivity", it.message) }
            )
}

/**
 * 隐藏键盘
 */
fun Activity.hideKeyboard(): Boolean {
    val view = currentFocus
    view?.let {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager
        return inputMethodManager.hideSoftInputFromWindow(view.windowToken,
                InputMethodManager.HIDE_NOT_ALWAYS)
    }
    return false
}

fun Any.logE(message: String) {
    LogUtils.e(this.javaClass.simpleName, message)
}

fun Any.logD(message: String) {
    LogUtils.d(this.javaClass.simpleName, message)
}