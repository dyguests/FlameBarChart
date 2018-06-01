package com.fanhl.util

import android.os.Build
import android.view.View

/**
 * 版本兼容处理
 *
 * @author fanhl
 */
object CompatibleHelper {
    fun postInvalidateOnAnimation(view: View?) {
        view?.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                postInvalidateOnAnimation()
            } else {
                postInvalidate()
            }
        }
    }
}