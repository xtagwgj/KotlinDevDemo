package com.xtagwgj.kotlindevdemo

/**
 * 初始化的服务
 * Created by xtagwgj on 2017/7/26.
 */
class InitService : com.xtagwgj.baseproject.base._InitializeService() {

    companion object {
        fun start(context: android.content.Context) {
            com.xtagwgj.baseproject.base._InitializeService.start(context, InitService::class.java)
        }
    }


    override fun initPush() {
    }

    override fun initHttp() {
//        AndroidNetworking.initialize(App.instance)
//        if (BuildConfig.DEBUG) {
//            AndroidNetworking.enableLogging(HttpLoggingInterceptor.Level.HEADERS)
//        }
    }

    override fun initLoadingLayout() {

    }

    override fun initLog() {
        com.xtagwgj.baseproject.utils.LogUtils.Builder(applicationContext)
                .setLog2FileSwitch(false)
                .setBorderSwitch(true)
                .setLogSwitch(BuildConfig.DEBUG)
    }

    override fun initOther() {
    }

    override fun initNet() {
    }

    override fun initLocalDataBase() {
    }
}