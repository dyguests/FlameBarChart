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
        set(value) {
            if (field == value) return

            field = value
            barWidthHalf = value / 2
        }
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

    // --------------------------------- 运算 ---------------------------------

    var barWidthHalf = 0

    /** 绘制x轴的内容的高度 */
    var xAxisContentHeight = 0
    /** 当前居中的x轴值 */
    var currentXAxis = 0
    /** 当前居中偏移值 (-0.5,0.5] */
    var currentXAxisOffsetPercent = 0f

    init {

        val resources = context.resources
        val a = context.obtainStyledAttributes(attrs, R.styleable.TravelChart, defStyleAttr, R.style.Widget_Travel_Chart)

        barWidth = a.getDimensionPixelOffset(R.styleable.TravelChart_barWidth, resources.getDimensionPixelOffset(R.dimen.bar_width))
        barInterval = a.getDimensionPixelOffset(R.styleable.TravelChart_barInterval, resources.getDimensionPixelOffset(R.dimen.bar_interval))
        barDrawable = a.getDrawable(R.styleable.TravelChart_barDrawable) ?: ContextCompat.getDrawable(context, R.drawable.selector_bar_drawable)

        xAxisPadding = a.getDimensionPixelOffset(R.styleable.TravelChart_xAxisPadding, resources.getDimensionPixelOffset(R.dimen.x_axis_padding))

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

        drawValid(canvas, validWidth, validHeight)

        canvas.restoreToCount(saveCount)
    }

    /**
     * 绘制区域
     */
    private fun drawValid(canvas: Canvas, validWidth: Int, validHeight: Int) {
        val barsWidth = validWidth
        val barsHeight = validHeight - xAxisPadding - xAxisContentHeight - xAxisPadding

        val barsPaddingLeft = 0f
        val barsPaddingTop = 0f

        val barsSaveCount = canvas.save()
        canvas.translate(barsPaddingLeft, barsPaddingTop)

        drawBars(canvas, barsWidth, barsHeight)

        canvas.restoreToCount(barsSaveCount)
    }

    /**
     * 绘制柱状态图区域
     */
    private fun drawBars(canvas: Canvas, barsWidth: Int, barsHeight: Int) {
        val horizontalMidpoint = barsWidth / 2

        barDrawable?.apply {
            setBounds(horizontalMidpoint - barWidthHalf, 0, horizontalMidpoint + barWidthHalf, barsHeight)
            draw(canvas)
        }

        canvas.drawCircle(100F, 100F, 100F, paint)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    /**
     * TravelChart的图表的数据结构
     */
    interface IData {
        /**
         * 获取y轴最大坐标值
         */
        fun getYAxisMin(): Float

        /**
         * 获取y轴最大坐标值
         */
        fun getYAxisMax(): Float
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

    /**
     * TravelChart要绘制的数据
     */
    class DefaultData<T : IItem> {
        val list = ArrayList<T>()
        // 添加数据时，判断数据是否在屏幕外，再决定是否 invalidate()
    }

    private data class DefaultItem(val y: Float) : IItem {
        override fun getYAxis(): Float {
            return y
        }
    }
}