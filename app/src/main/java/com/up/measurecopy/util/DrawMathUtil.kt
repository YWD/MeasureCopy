package com.up.measurecopy.util

import android.graphics.Point
import android.graphics.PointF

/**
 * Created by ywd on 2017/11/6.
 * 画图数学计算工具
 */
class DrawMathUtil  {

    fun getDistanceFromTwoPoint(point1: Point, point2: Point):Float =
            Math.sqrt(Math.pow(((point1.x - point2.x).toDouble()), 2.0) + Math.pow(((point1.y - point2.y).toDouble()), 2.0)).toFloat()
}