package com.up.measurecopy.component.drawview

import android.graphics.Point
import android.graphics.PointF
import android.view.MotionEvent
import android.view.View
import com.up.measurecopy.config.AppConstants
import com.up.measurecopy.util.DrawMathUtil

/**
 * Created by ywd on 2017/11/4.
 * DrawView的手指滑动监听
 */
class DrawTouchListener(private val drawView: DrawView) : View.OnTouchListener {

    private val mathUtil = DrawMathUtil()

    init {

    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                if (drawView.activePoint != null) {
                    if (checkGetActivePoint(event.x, event.y)) {
                        drawView.points.add(drawView.activePoint!!)
                        drawView.activePoint = Point(event.x.toInt(), event.y.toInt())
                        drawView.points.add(drawView.activePoint!!)
                        drawView.invalidate()
                    }
                }
            }
        }
        return true
    }

    private fun checkGetActivePoint(x: Float, y: Float) =
            mathUtil.getDistanceFromTwoPoint(Point(x.toInt(), y.toInt()), drawView.activePoint!!) < AppConstants.CIRCLE_DRAW_RADIUS

}