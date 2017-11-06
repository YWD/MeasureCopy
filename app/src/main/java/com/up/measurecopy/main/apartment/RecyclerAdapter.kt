package com.up.measurecopy.main.apartment

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.up.measurecopy.R
import com.up.measurecopy.model.Apartment

/**
 * Created by ywd on 2017/11/2.
 * RecyclerView adapter
 */
class RecyclerAdapter(val data: List<Apartment>) : RecyclerView.Adapter<RecyclerAdapter.MyHolder>() {

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.apartmentPosition.text = "${data[position].community_name}  ${data[position].house_type}"
    }

    override fun getItemCount(): Int = data.size

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyHolder{
        val itemView = LayoutInflater.from(parent?.context).inflate(R.layout.item_apartment_info, parent, false)
//        parent?.addView(itemView)
        return MyHolder(itemView)
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var apartmentPosition = itemView.findViewById(R.id.apartmentPosition) as TextView
    }
}


