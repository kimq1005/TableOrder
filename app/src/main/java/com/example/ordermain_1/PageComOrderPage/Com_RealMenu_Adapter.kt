package com.example.ordermain_1.PageComOrderPage

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ordermain_1.PageGoOrderPage.RealmenuDatabase.OnDeleteListener
import com.example.ordermain_1.PageGoOrderPage.RealmenuDatabase.RealmenuEntity
import com.example.ordermain_1.R
import kotlinx.android.synthetic.main.activity_go_order_page.view.*
import kotlinx.android.synthetic.main.item_layout_com_order.view.*
import kotlin.coroutines.coroutineContext

class Com_RealMenu_Adapter(var onDeleteListener: OnDeleteListener):RecyclerView.Adapter<Com_RealMenu_Adapter.ComOrderViewHolder>() {

    lateinit var realmenuList : List<RealmenuEntity>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComOrderViewHolder {
       return ComOrderViewHolder(
           LayoutInflater.from(parent.context).inflate(R.layout.item_layout_com_order,parent,false)
       )
    }

    override fun onBindViewHolder(holder: ComOrderViewHolder, position: Int) {

        val realmenupostion = realmenuList[position]
        holder.Holder_realmenuname.text = realmenupostion.realmenuname
        holder.Holder_realmenuprice.text = realmenupostion.realmenuprice
        holder.Holder_realmenusocre.text = realmenupostion.realmenufoodscore
        holder.Holder_realmenudelete.setOnClickListener {
            onDeleteListener.onrealmenuDeleteListner(realmenupostion)
        }





    }

    override fun getItemCount(): Int {
        return realmenuList.size
    }

    class ComOrderViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        val Holder_realmenuname = itemView.com_menuname_txt
        val Holder_realmenuprice = itemView.com_price_txt
        val Holder_realmenudelete = itemView.com_delete_btn
        val Holder_realmenusocre = itemView.com_menuscore

    }

    fun submitList(list:List<RealmenuEntity>){
        realmenuList = list
        notifyDataSetChanged()
    }

}