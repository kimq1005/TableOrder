package com.example.ordermain_1.PageComOrderPage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ordermain_1.PageGoOrderPage.RealmenuDatabase.RealmenuEntity
import com.example.ordermain_1.R
import kotlinx.android.synthetic.main.activity_go_order_page.view.*
import kotlinx.android.synthetic.main.item_layout_com_order.view.*
import kotlinx.android.synthetic.main.item_layout_realmenu.view.*

class ComOrder_Adapter:RecyclerView.Adapter<ComOrder_Adapter.ComOrderViewHolder>() {

    lateinit var realmenuList : List<RealmenuEntity>



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComOrderViewHolder {
       return ComOrderViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.item_layout_com_order,parent,false)
       )
    }

    override fun onBindViewHolder(holder: ComOrderViewHolder, position: Int) {
        val realmenupostion = realmenuList[position]
        holder.activityrealmenuname.text = realmenupostion.realmenuname
        holder.activityrealmenuprice.text = realmenupostion.realmenuprice
    }

    override fun getItemCount(): Int {
        return realmenuList?.size ?:0
    }

    class ComOrderViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        val activityrealmenuname = itemView.OrderPage_menuName_txt
        val activityrealmenuprice = itemView.OrderPage_menuPrice_txt

    }

    fun submitList(list:List<RealmenuEntity>){
        realmenuList = list
        notifyDataSetChanged()
    }






}