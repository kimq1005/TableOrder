package com.example.ordermain_1.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ordermain_1.Item.ComOrderItem
import com.example.ordermain_1.Item.Menu_informationItem
import com.example.ordermain_1.R
import kotlinx.android.synthetic.main.activity_completed_order_page.view.*
import kotlinx.android.synthetic.main.item_layout_com_order.view.*

class ComOrder_Adapter:RecyclerView.Adapter<ComOrder_Adapter.ComOrderViewHolder>() {

    private var comorderitemList : ArrayList<ComOrderItem>? =null




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComOrderViewHolder {
       return ComOrderViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.item_layout_com_order,parent,false)
       )
    }

    override fun onBindViewHolder(holder: ComOrderViewHolder, position: Int) {
        comorderitemList?.let{
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return comorderitemList?.size ?:0
    }

    class ComOrderViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){
        fun bind(comOrderItem: ComOrderItem){
            itemView.com_menuname_txt.text = comOrderItem.comodername
            itemView.com_price_txt.text = comOrderItem.comoderprice
        }
    }

    fun submitList(list:ArrayList<ComOrderItem>?){
        comorderitemList = list
        notifyDataSetChanged()
    }

    fun additemList(comOrderItem:ComOrderItem){
        comorderitemList?.add(comOrderItem)
        notifyDataSetChanged()
    }

    fun removeItem(position: Int){
        if(position >0){
            comorderitemList?.removeAt(position)
            notifyDataSetChanged()
        }
    }


}