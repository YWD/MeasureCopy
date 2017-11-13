package com.up.measurecopy.component.drawview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Point
import android.util.AttributeSet
import android.view.View

/**
 * Created by ywd on 2017/11/4.
 * 绘图View
 */
class DrawView : View {

    var hasActivePoint: Boolean = true
    var activePoint: Point = Point(0,0)
    var isTouched: Boolean = false
    val points: MutableList<Point> = ArrayList()
    private val drawHelper = DrawHelper(context)

    constructor(context: Context, attrs: AttributeSet?, defStyleStr: Int) : super(context, attrs, defStyleStr)
    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0)
    constructor(context: Context) : super(context)

    init {
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        if (hasActivePoint) {
            if (activePoint == Point(0,0)) {
                activePoint = Point(measuredWidth / 2, measuredHeight / 2)
                points.add(activePoint)
            }
            drawHelper.drawActivePoint(canvas!!, activePoint)
        }

        points.add(activePoint)
        drawHelper.drawLine(canvas!!, points)
        points.remove(activePoint)

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

}


