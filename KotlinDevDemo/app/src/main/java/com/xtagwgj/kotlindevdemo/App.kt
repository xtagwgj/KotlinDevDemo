package com.xtagwgj.kotlindevdemo

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import com.xtagwgj.baseproject.base._MyApplication
import com.xtagwgj.baseproject.utils.LogUtils
import com.xtagwgj.baseproject.utils.NetWorkUtils

/**
 * Application
 * Created by xtagwgj on 2017/7/26.
 */

class App : _MyApplication() {

    init {
        instance = this
    }

    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()

        registerReceiver(mReceiver, IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"))

    }

    //通过IntentService进行初始化
    override fun initService() {
        InitService.start(this)
    }

    var netIsAvailable: Boolean = true

    //广播接收者，在这里接收一些需要使用到的系统广播
    var mReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {

            //网络变化
            if (intent?.action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
                NetWorkUtils.isAvailableByPing()
                        .subscribe({ connect -> netIsAvailable = connect },
                                { error -> LogUtils.e(error.message) })
            }

        }
    }

}