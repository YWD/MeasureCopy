package com.up.measurecopy.component.drawview

import android.graphics.Point
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
                if (checkGetActivePoint(event.x, event.y)) {
                    drawView.isTouched = true
//                    drawView.points.add(drawView.activePoint!!)
                    drawView.activePoint = Point(event.x.toInt(), event.y.toInt())
                    drawView.invalidate()
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (drawView.isTouched) {
                    drawView.activePoint = Point(event.x.toInt(), event.y.toInt())
                    mathUtil.correctActivePoint(drawView.activePoint, drawView.points[drawView.points.size - 1])
                    drawView.invalidate()
                }
            }
            MotionEvent.ACTION_UP -> {
                drawView.isTouched = false
                drawView.points.add(drawView.activePoint)
            }
        }
        return true
    }

    private fun checkGetActivePoint(x: Float, y: Float): Boolean {
        if (drawView.activePoint == Point(0,0)) {
            return false
        }
        return mathUtil.getDistanceBetweenTwoPoint(Point(x.toInt(), y.toInt()), drawView.activePoint) < AppConstants.CIRCLE_DRAW_RADIUS
    }

}

