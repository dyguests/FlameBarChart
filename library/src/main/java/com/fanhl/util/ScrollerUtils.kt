package com.fanhl.util

import android.content.Context
import android.hardware.SensorManager
import android.view.ViewConfiguration

/**
 * Scroller帮助类
 */
object ScrollerUtils {
    private val INFLEXION = 0.35f // Tension lines cross at (INFLEXION, 1)
    // Fling friction
    private val mFlingFriction = ViewConfiguration.getScrollFriction()
    private var mPhysicalCoeff: Float = 0.toFloat()
    private val DECELERATION_RATE = (Math.log(0.78) / Math.log(0.9)).toFloat()

    fun init(context: Context) {
        val ppi = context.getResources().getDisplayMetrics().density * 160.0f
        mPhysicalCoeff = (SensorManager.GRAVITY_EARTH // g (m/s^2)

                * 39.37f // inch/meter

                * ppi
                * 0.84f) // look and feel tuning
    }

    /**
     * 获取减速
     */
    private fun getSplineDeceleration(velocity: Int): Double {
        return Math.log((INFLEXION * Math.abs(velocity) / (mFlingFriction * mPhysicalCoeff)).toDouble())
    }

    /**
     * 获取减速 by 距离
     */
    private fun getSplineDecelerationByDistance(distance: Double): Double {
        val decelMinusOne = DECELERATION_RATE - 1.0
        return decelMinusOne * Math.log(distance / (mFlingFriction * mPhysicalCoeff)) / DECELERATION_RATE
    }

    /**
     * 通过初始速度获取最终滑动距离
     */
    fun getSplineFlingDistance(velocity: Int): Double {
        val l = getSplineDeceleration(velocity)
        val decelMinusOne = DECELERATION_RATE - 1.0
        return mFlingFriction * mPhysicalCoeff * Math.exp(DECELERATION_RATE / decelMinusOne * l)
    }

    /**
     * 通过需要滑动的距离获取初始速度
     */
    fun getVelocityByDistance(distance: Double): Int {
        val l = getSplineDecelerationByDistance(distance)
        val velocity = (Math.exp(l) * mFlingFriction * mPhysicalCoeff / INFLEXION) as Int
        return Math.abs(velocity)
    }

    /**
     *  Returns the duration, expressed in milliseconds
     */
    fun getSplineFlingDuration(velocity: Int): Int {
        val l = getSplineDeceleration(velocity)
        val decelMinusOne = DECELERATION_RATE - 1.0
        return (1000.0 * Math.exp(l / decelMinusOne)).toInt()
    }
}