package com.up.measurecopy.util

import android.graphics.Point

/**
 * Created by ywd on 2017/11/6.
 * 画图数学计算工具
 */
class DrawMathUtil {

    private val tan15 = Math.tan(Math.toRadians(15.0))

    fun getDistanceBetweenTwoPoint(point1: Point, point2: Point): Float =
            Math.sqrt(Math.pow(((point1.x - point2.x).toDouble()), 2.0) + Math.pow(((point1.y - point2.y).toDouble()), 2.0)).toFloat()

    fun correctActivePoint(activePoint: Point, point: Point) {
        val deltaX = Math.abs(activePoint.x - point.x)
        val deltaY = Math.abs(activePoint.y - point.y)
        if (deltaY > deltaX && (deltaX.toFloat() / deltaY) < tan15) {
            activePoint.x = point.x
        } else if (deltaX > deltaY && (deltaY.toFloat() / deltaX) < tan15) {
            activePoint.y = point.y
        }
    }
}