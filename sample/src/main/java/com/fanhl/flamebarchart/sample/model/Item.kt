package com.fanhl.flamebarchart.sample.model

import com.fanhl.flamebarchart.TravelChart

data class Item(val x: Int, val y: Float) : TravelChart.IItem {
    override fun getXLabel(): String {
        return if (Math.abs(x - 15) <= 0.01f) "Today" else "$x"
    }

    override fun getXHint(): String {
        return "${(x * y * 100).toInt()}km"
    }

    override fun getYAxis(): Float {
        return y
    }

}