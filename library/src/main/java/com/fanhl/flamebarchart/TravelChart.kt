package com.fanhl.flamebarchart

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.drawable.Drawable
import android.support.v4.content.ContextCompat
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
    /** 柱宽 */
    var barWidth = 0
    /** 柱间距 */
    var barInterval = 0
    /** 柱子的背景图 */
    var barDrawable: Drawable? = null
    /** x轴的上下间距 */
    var xAxisPadding = 0

    var data: DefaultData<*>? = null
        set(value) {
            field = value
            invalidate()
        }

    init {

        val resources = context.resources
        val a = context.obtainStyledAttributes(attrs, R.styleable.TravelChart, defStyleAttr, R.style.Widget_Travel_Chart)

        barWidth = a.getDimensionPixelOffset(R.styleable.TravelChart_barWidth, resources.getDimensionPixelOffset(R.dimen.bar_width))
        barInterval = a.getDimensionPixelOffset(R.styleable.TravelChart_barInterval, resources.getDimensionPixelOffset(R.dimen.bar_interval))
        barDrawable = a.getDrawable(R.styleable.TravelChart_barDrawable) ?: ContextCompat.getDrawable(context, R.drawable.selector_bar_drawable)

        a.recycle()

        if (isInEditMode) {
            val random = Random()
            data = DefaultData<DefaultItem>().apply {
                (1..20).forEach { list.add(DefaultItem(random.nextFloat())) }
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val validWidth = width - paddingLeft - paddingRight
        val validHeight = height - paddingTop - paddingBottom

        val saveCount = canvas.save()
        canvas.translate(paddingLeft.toFloat(), paddingRight.toFloat())

        draw(canvas, validWidth, validHeight)

        canvas.restoreToCount(saveCount)
    }

    /**
     * 绘制区域
     */
    private fun draw(canvas: Canvas, validWidth: Int, validHeight: Int) {
        canvas.drawCircle(100F, 100F, 100F, paint)
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