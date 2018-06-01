package com.fanhl.flamebarchart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import android.widget.OverScroller
import java.util.*

/**
 * 数据图表
 *
 * @author fanhl
 */
class TravelChart @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint by lazy { Paint() }
    private val path by lazy { Path() }
    private val scroller by lazy { OverScroller(context) }

    // --------------------------------- 输入 ---------------------------

    var data: DefaultData<*>? = null
        set(value) {
            field = value
            invalidate()
        }

    init {
        if (isInEditMode) {
            val random = Random()
            data = DefaultData<DefaultItem>().apply {
                list.apply {
                    fun add(y: Float) {
                        add(DefaultItem(y))
                    }
                    add(random.nextFloat())
                    add(random.nextFloat())
                    add(random.nextFloat())
                    add(random.nextFloat())
                    add(random.nextFloat())

                    add(random.nextFloat())
                    add(random.nextFloat())
                    add(random.nextFloat())
                    add(random.nextFloat())
                    add(random.nextFloat())

                    add(random.nextFloat())
                    add(random.nextFloat())
                    add(random.nextFloat())
                    add(random.nextFloat())
                    add(random.nextFloat())

                    add(random.nextFloat())
                    add(random.nextFloat())
                    add(random.nextFloat())
                    add(random.nextFloat())
                    add(random.nextFloat())
                }
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val validWidth = width - paddingLeft - paddingRight

        val saveCount = canvas.save()
        canvas.translate(paddingLeft.toFloat(), paddingRight.toFloat())

        canvas.drawCircle(100F, 100F, 100F, paint)

        canvas.restoreToCount(saveCount)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    /**
     * TravelChart要绘制的数据
     */
    class DefaultData<T : IItem> {
        val list = ArrayList<T>()
        // 添加数据时，判断数据是否在屏幕外，再决定是否 invalidate()
    }

    /**
     * TravelChart的图表上关键点的数据结构
     */
    interface IItem {
        /**
         * 获取y轴坐标值
         */
        fun getYAxis(): Float
    }

    private data class DefaultItem(val y: Float) : IItem {
        override fun getYAxis(): Float {
            return y
        }
    }
}