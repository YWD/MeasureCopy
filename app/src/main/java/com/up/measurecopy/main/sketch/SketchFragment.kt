package com.up.measurecopy.main.sketch

import android.app.Fragment
import android.graphics.Point
import android.graphics.PointF
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.up.measurecopy.R
import com.up.measurecopy.component.drawview.DrawTouchListener
import com.up.measurecopy.component.drawview.DrawView
import com.up.measurecopy.util.LogUtil

/**
 * Created by ywd on 2017/11/3.
 * SketchFragment
 */
class SketchFragment : Fragment() {

    var contentView:View ? = null
    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        contentView = inflater?.inflate(R.layout.fragment_sketch, container, false)
        return contentView!!
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        val drawView = contentView?.findViewById(R.id.drawView) as DrawView
        val measuredWidth = drawView.measuredWidth
        LogUtil.d("ywd", "measuredWidth:" + measuredWidth)
        drawView.activePoint = Point((measuredWidth / 2), (drawView.measuredHeight / 2))
        drawView.invalidate()
        drawView.setOnTouchListener(DrawTouchListener(drawView))
    }
}