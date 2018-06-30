package com.fanhl.flamebarchart.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.fanhl.flamebarchart.TravelChart
import com.fanhl.flamebarchart.sample.model.Item
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        chart_travel.addOnXAxisChangeListeners(object : TravelChart.DefaultOnXAxisChangeListener() {
            override fun onCurrentXAxisChanged(currentXAxis: Int) {
                Log.d(TAG, "onCurrentXAxisChanged: currentXAxis:$currentXAxis")
            }

            override fun onCurrentXAxisOffsetChanged(currentXAxis: Int, currentXAxisOffset: Float, velocity: Float) {
                Log.d(TAG, "onCurrentXAxisOffsetChanged: currentXAxis:$currentXAxis,currentXAxisOffset:$currentXAxisOffset,velocity:$velocity")
            }

            override fun oScrollEnd(currentXAxis: Int) {
                Toast.makeText(this@MainActivity, "oScrollEnd$currentXAxis", Toast.LENGTH_SHORT).show()
            }
        })
        fab.setOnClickListener {
            rebindData()
        }

        rebindData()
        chart_travel.setXAxis(10)
    }

    private fun rebindData() {
        val random = Random()
        chart_travel.data = TravelChart.DefaultData<Item>().apply {
            (1..20).forEach { list.add(Item(it, random.nextFloat().takeIf { it > 0.3f })) }
        }
        tv_max_visible_count.text = "在屏幕中最多可以同时显示多少条柱：\n${chart_travel.maxVisibleCount}"
    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}
