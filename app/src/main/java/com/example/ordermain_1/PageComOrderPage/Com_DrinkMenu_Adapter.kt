package com.example.ordermain_1.PageComOrderPage

import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ordermain_1.PageGoOrderPage.DrinkmenuDatabase.DrinkmenuEntity
import com.example.ordermain_1.PageGoOrderPage.RealmenuDatabase.OnDeleteListener
import com.example.ordermain_1.R
import kotlinx.android.synthetic.main.item_layout_com_order.view.*

class Com_DrinkMenu_Adapter(var onDeleteListener: OnDeleteListener):RecyclerView.Adapter<Com_DrinkMenu_Adapter.Com_drinkmenuViewHolder>() {
    lateinit var drinkmenuList : List<DrinkmenuEntity>



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Com_drinkmenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_com_order,parent,false)
        return Com_drinkmenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: Com_drinkmenuViewHolder, position: Int) {
        val drinkmenupostion = drinkmenuList[position]

        holder.Holder_drinkmenuname.text = drinkmenupostion.drinkmenuname
        holder.Holder_drinkmenuprice.text = drinkmenupostion.drinkmenuprice
        holder.Holder_drinkmenusocre.text = drinkmenupostion.drinkmenufoodscore

        holder.Holder_drinkmenuDeleteBtn.setOnClickListener {
            onDeleteListener.ondrinkmenuDeleteListener(drinkmenupostion)
        }
    }

    override fun getItemCount(): Int {
        return drinkmenuList.size
    }

    class Com_drinkmenuViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val Holder_drinkmenuname = itemView.com_menuname_txt
        val Holder_drinkmenuprice = itemView.com_price_txt
        val Holder_drinkmenuDeleteBtn = itemView.com_delete_btn
        val Holder_drinkmenusocre = itemView.com_menuscore

    }

    fun submitList(list: List<DrinkmenuEntity>){
        drinkmenuList = list
        notifyDataSetChanged()
    }

}