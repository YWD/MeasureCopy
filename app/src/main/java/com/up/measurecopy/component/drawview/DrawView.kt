package com.up.measurecopy.component.drawview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.up.measurecopy.R
import com.up.measurecopy.config.AppConstants
import com.up.measurecopy.util.LogUtil

/**
 * Created by ywd on 2017/11/4.
 * 绘图View
 */
class DrawView : View {

    var activePoint: Point? = null
    val points: MutableList<Point> = ArrayList()
    private val drawHelper = DrawHelper(context)

    constructor(context: Context, attrs: AttributeSet?, defStyleStr: Int) : super(context, attrs, defStyleStr)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : super(context)

    init {
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        drawHelper.drawActivePoint(canvas!!, activePoint)
        drawHelper.drawLine(canvas, points)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        LogUtil.d("ywd", "widthMeasureSpec:" +measuredWidth)
        LogUtil.d("ywd", "widthMeasureSpec:" +measuredHeight)
    }
}


