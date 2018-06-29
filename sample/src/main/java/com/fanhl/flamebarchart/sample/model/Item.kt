package com.fanhl.flamebarchart.sample.model

import com.fanhl.flamebarchart.TravelChart
import com.fanhl.flamebarchart.sample.util.SpanUtils
import com.fanhl.flamebarchart.sample.util.px

data class Item(val x: Int, val y: Float?) : TravelChart.IItem {
    override fun getXLabel(): CharSequence {
        return if (Math.abs(x - 15) <= 0.01f) "Today" else "$x"
    }

    override fun getXHint(): CharSequence {
//        return "${(x * y * 100).toInt()}km"
        return SpanUtils()
                .append("${(x * (y ?: 0f) * 100).toInt()}")
                .append(" ")
//                .append("km")
                .append("km").setFontSize(11.px)
                .create()
    }

    override fun getYAxis(): Float? {
        return y
    }

}