package com.xtagwgj.kdev.base

import android.app.Activity
import android.content.Intent
import android.os.Build
import com.xtagwgj.baseproject.base.AppManager
import com.xtagwgj.baseproject.base._BaseActivity
import com.xtagwgj.baseproject.utils.AndroidDeviceUtil
import com.xtagwgj.kdev.R

/**
 * 基类
 * Created by xtagwgj on 2017/7/26.
 */
abstract class BaseActivity : _BaseActivity() {

    companion object {
        fun startByAnim(mContext: Activity, intent: Intent, requestCode: Int = -1, finishOld: Boolean = false) {
            if (requestCode != -1)
                mContext.startActivityForResult(intent, requestCode)
            else
                mContext.startActivity(intent)

            if (AndroidDeviceUtil.getSDKVersion() < Build.VERSION_CODES.M) {
                startAnim(mContext)
            }

            if (finishOld)
                AppManager.getAppManager().finishActivity(mContext)
        }

        fun destroyByAnim(mContext: Activity, requestCode: Int = -1) {
            if (requestCode != -1)
                mContext.finishActivity(requestCode)
            else
                mContext.finish()


            AppManager.getAppManager().finishActivity(mContext)

            if (AndroidDeviceUtil.getSDKVersion() < Build.VERSION_CODES.M) {
                stopAnim(mContext)
            }
        }

        private fun startAnim(mContext: Activity) {
            mContext.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left)
        }

        private fun stopAnim(mContext: Activity) {
            mContext.overridePendingTransition(R.anim.in_from_left, R.anim.out_to_right)
        }
    }

}