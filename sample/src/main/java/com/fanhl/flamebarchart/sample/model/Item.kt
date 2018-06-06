package com.fanhl.flamebarchart.sample.model

import android.graphics.Color
import com.fanhl.flamebarchart.TravelChart
import com.fanhl.flamebarchart.sample.util.SpanUtils

data class Item(val x: Int, val y: Float) : TravelChart.IItem {
    override fun getXLabel(): CharSequence {
        return if (Math.abs(x - 15) <= 0.01f) "Today" else "$x"
    }

    override fun getXHint(): CharSequence {
//        return "${(x * y * 100).toInt()}km"
        return SpanUtils()
                .append("${(x * y * 100).toInt()}")
//                .append("km")
                .append("km").setFontSize(28)
                .create()
    }

    override fun getYAxis(): Float {
        return y
    }

}