package com.example.ordermain_1.PageComOrderPage

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ordermain_1.PageGoOrderPage.RealmenuDatabase.OnDeleteListener
import com.example.ordermain_1.PageGoOrderPage.RealmenuDatabase.RealmenuEntity
import com.example.ordermain_1.PageGoOrderPage.SidemenuDatabase.SidemenuEntity
import com.example.ordermain_1.R
import com.example.ordermain_1.rerofit.test21
import kotlinx.android.synthetic.main.item_layout_com_order.view.*

class Com_SideMenu_Adapter(var onDeleteListener: OnDeleteListener):RecyclerView.Adapter<Com_SideMenu_Adapter.Com_SidemenuViewHolder>() {

    lateinit var sidemenuList : List<SidemenuEntity>
    lateinit var  test21  : ArrayList<test21>


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Com_SidemenuViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_com_order,parent,false)
        return Com_SidemenuViewHolder(view)
    }

    override fun onBindViewHolder(holder: Com_SidemenuViewHolder, position: Int) {
        val sidemenupostion = sidemenuList[position]

        holder.Holder_sidemenuname.text = sidemenupostion.sidemenuname
        holder.Holder_sidemenuprice.text = sidemenupostion.sidemenuprice
        holder.Holder_drinkmenuscore.text = sidemenupostion.sidemenufoodscore

        holder.Holder_deletebtn.setOnClickListener {
           onDeleteListener.onsidemenuDeleteListener(sidemenupostion)
        }
    }

    override fun getItemCount(): Int {
        return sidemenuList.size
    }

    class Com_SidemenuViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val Holder_sidemenuname = itemView.com_menuname_txt
        val Holder_sidemenuprice = itemView.com_price_txt
        val Holder_deletebtn = itemView.com_delete_btn
        val Holder_drinkmenuscore = itemView.com_menuscore

    }

    fun submitList(list:List<SidemenuEntity>){
        sidemenuList = list
        notifyDataSetChanged()
    }
}