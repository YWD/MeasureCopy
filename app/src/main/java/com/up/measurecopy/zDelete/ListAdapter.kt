package com.up.measurecopy.delete.adapter

import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

/**
 * Created by ywd on 2017/10/27.
 * ListView Adapter
 */
class ListAdapter(private val context: Context,private val stringData: ArrayList<String>) : BaseAdapter() {
    override fun getItem(position: Int): Any = stringData[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?):View {
        return if (convertView === null) {
            val textView = TextView(context)
            textView.text = stringData[position]
            Log.d("ywd", "初始化")
            textView
        } else {
            convertView as TextView
            convertView.text = "复用"
            Log.d("ywd", "复用")
            convertView
        }
    }

    override fun getCount(): Int {
        Log.d("ywd", "stringData.size:" + stringData.size)
        return stringData.size
    }
}