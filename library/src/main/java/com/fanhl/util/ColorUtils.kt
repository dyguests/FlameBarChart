package com.fanhl.util

import android.graphics.Color
import android.support.annotation.ColorInt
import android.support.annotation.FloatRange

object ColorUtils {
    /**
     * 颜色渐变
     *
     * 为0时,颜色为xLabelTextColorFocused，为1时颜色为xLabelTextColor，为(0,1)之间时，取这两者之间的值
     */
    @ColorInt
    fun getColorGradient(@ColorInt colorStart: Int, @ColorInt colorEnd: Int, @FloatRange(from = 0.0, to = 1.0) percent: Float): Int {
        return Color.argb(
                getColorOfDegradateCalculation(Color.alpha(colorStart), Color.alpha(colorEnd), percent),
                getColorOfDegradateCalculation(Color.red(colorStart), Color.red(colorEnd), percent),
                getColorOfDegradateCalculation(Color.green(colorStart), Color.green(colorEnd), percent),
                getColorOfDegradateCalculation(Color.blue(colorStart), Color.blue(colorEnd), percent)
        )
    }

    private fun getColorOfDegradateCalculation(colorStart: Int, colorEnd: Int, percent: Float): Int {
        return (colorStart * (1 - percent) + colorEnd * percent).toInt()
    }
}