package com.example.ordermain_1.PageMenuPageUI

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ordermain_1.PageGoOrderPage.GoSideOrderPage
import com.example.ordermain_1.R
import kotlinx.android.synthetic.main.item_layout_sidemenu.view.*


class Sidemenu_Adapter:RecyclerView.Adapter<Sidemenu_Adapter.SideMenuViewHolder>() {

    private var sidemenuItemList : List<SidemenuItem>?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SideMenuViewHolder {
        return SideMenuViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_layout_sidemenu,parent,false)
        )
    }

    override fun onBindViewHolder(holder: SideMenuViewHolder, position: Int) {
        sidemenuItemList?.let{
            holder.bind(it[position])
        }
    }

    override fun getItemCount(): Int {
        return sidemenuItemList?.size ?:0
    }

    class SideMenuViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, GoSideOrderPage::class.java)
                intent.putExtra("side_menu_img",itemView.side_menu_img.toString())
                intent.putExtra("side_menu_name",itemView.side_menuname_txt.text)
                intent.putExtra("side_menu_price",itemView.side_menuprice_txt.text)
                itemView.context.startActivity(intent)
            }
        }



        private val sidemenuImg : ImageView = itemView.side_menu_img


        fun bind(sidemeunuitem: SidemenuItem){

            Glide.with(itemView).load(sidemeunuitem.sidemenuimg).into(sidemenuImg)
            itemView.side_menuname_txt.text = sidemeunuitem.sidemenuname
            itemView.side_menuinformation_txt.text = sidemeunuitem.sidemenuinformation
            itemView.side_menuprice_txt.text = sidemeunuitem.sidemenuprice.toString()

        }


    }


    fun submitList(list:List<SidemenuItem>?){
        sidemenuItemList = list
        notifyDataSetChanged()
    }


}