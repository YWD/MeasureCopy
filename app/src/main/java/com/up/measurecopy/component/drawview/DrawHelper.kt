package com.up.measurecopy.component.drawview

import android.content.Context
import android.graphics.*
import com.up.measurecopy.R
import com.up.measurecopy.config.AppConstants
import com.up.measurecopy.util.CommonUtil
import com.up.measurecopy.util.LogUtil

/**
 * Created by ywd on 2017/11/4.
 * 绘图工具
 */
class DrawHelper(val context: Context) {

    private val paint = Paint()
    private val resources = context.resources

    init {
        paint.flags = Paint.ANTI_ALIAS_FLAG
    }

    fun drawActivePoint(canvas: Canvas, point: Point?) {
        if (point == null) {
            return
        }
        paint.style = Paint.Style.FILL
        paint.color = resources.getColor(R.color.circle_draw)
        canvas.drawCircle(point.x.toFloat(), point.y.toFloat(), AppConstants.CIRCLE_DRAW_RADIUS, paint)
        paint.color = resources.getColor(R.color.red)
        paint.style = Paint.Style.STROKE
        paint.strokeWidth = CommonUtil.dp2px(context, 1).toFloat()
        canvas.drawCircle(point.x.toFloat(), point.y.toFloat(), AppConstants.CIRCLE_DRAW_RADIUS * 0.6F, paint)
    }

    fun drawLine(canvas: Canvas, points: List<Point>) {
        if (points.size < 2) {
            return
        }
        val path = Path()
        val point = points[0]
        path.moveTo(point.x.toFloat(), point.y.toFloat())
        for ((x, y) in points) {
            path.lineTo(x.toFloat(), y.toFloat())
        }
        paint.color = resources.getColor(R.color.black)
        canvas.drawPath(path, paint)
    }
}

private operator fun Point.component1(): Int = x
private operator fun Point.component2(): Int = y
