package com.xtagwgj.kdev.extensions

import android.support.annotation.IntRange
import android.support.annotation.LayoutRes
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.xtagwgj.baseproject.utils.RegexUtil

/**
 * view的扩展事件
 * Created by xtagwgj on 2017/7/26.
 */
fun EditText.isPhone(): Boolean = RegexUtil.checkCellPhone(text.toString().trim())

fun EditText.isEmail(): Boolean = RegexUtil.checkEmail(text.toString().trim())

fun EditText.isNumber(): Boolean = RegexUtil.isNumeric(text.toString().trim())

fun EditText.isIdCard(): Boolean = RegexUtil.IDCardValidate(text.toString().trim())

/**
 * 判断位数是否足够
 */
fun EditText.isLengthEnough(@IntRange(from = 0) number: Int): Boolean {
    return text.toString().trim().length >= number
}

/**
 * 显示或隐藏密码
 *
 * @return  true 可以看到密码
 *          false 不可以看到密码
 */
fun EditText.showOrHidePwd(): Boolean {
    val flag = transformationMethod == PasswordTransformationMethod.getInstance()

    if (flag) {
        transformationMethod = HideReturnsTransformationMethod.getInstance()
    } else {
        transformationMethod = PasswordTransformationMethod.getInstance()
    }

    //光标移动到最后
    setSelection(text.toString().trim().length)
    return flag
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int): View {
    return LayoutInflater.from(context).inflate(layoutRes, this, false)
}