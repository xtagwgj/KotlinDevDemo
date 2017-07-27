package com.xtagwgj.kotlindevdemo

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.KeyEvent
import com.bumptech.glide.Glide
import com.xtagwgj.baseproject.base.AppManager
import com.xtagwgj.baseproject.utils.ToastUtils
import com.xtagwgj.kdev.base.BaseActivity
import com.xtagwgj.kdev.extensions.isNumber
import com.xtagwgj.kdev.extensions.showOrHidePwd
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.*
import org.jetbrains.anko.sdk21.coroutines.onClick

class MainActivity : BaseActivity() {

    val city: String by lazy {
        ""
    }

    override fun initEventListener() {

        clickEvent(button, {
            val flag = passwordText.showOrHidePwd()

            alert({

                customView {
                    verticalLayout {
                        padding = dip(20)

                        textView {
//                            paddingBottom = dip(8)
                            text = "title"
                            textSize = sp(16F).toFloat()
                            gravity = Gravity.CENTER_HORIZONTAL
                        }

                        val nameEditText = editText {
                            setTextColor(Color.parseColor("#cc123456"))
                            setText("1234")
                        }

                        button("cancel") {

                            onClick {
                                longToast(nameEditText.text.toString())

                            }

                        }
                    }

                }

                okButton {
                    AnkoLogger(javaClass).debug {
                        "$flag ${passwordText.isNumber()}"
                    }
                }

                noButton {
                    AnkoLogger(javaClass).error {
                        "$flag ${passwordText.isNumber()}"
                    }
                }


            }).show()

        })

    }

    override fun initView(p0: Bundle?) {


        Glide.with(this)
                .load("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=4236348291,4068822761&fm=26&gp=0.jpg")
                .into(imageView)

//        GlideApp.with(this)

        val intList = arrayListOf(1, 2, 3, 4, 5, 3, 0, -1)

        val sortedDescending = intList.filter { it % 2 != 0 }
                .filterNot { it < 0 }
                .sortedDescending()
                .filter { it > 6 }
                .filterNotNull()

        println("cal= $sortedDescending")


        AnkoLogger(javaClass).debug {

        }


    }

    override fun getLayoutId(): Int = R.layout.activity_main


    //返回退出
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
