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
import com.example.ordermain_1.rerofit.MainMenulist
import com.example.ordermain_1.rerofit.SideMenulist
import kotlinx.android.synthetic.main.item_layout_sidemenu.view.*
import kotlinx.android.synthetic.main.layout_realmenu_item.view.*


class Sidemenu_Adapter:RecyclerView.Adapter<Sidemenu_Adapter.SideMenuViewHolder>() {

    private var sidemenulist = ArrayList<SideMenulist>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SideMenuViewHolder {
        return SideMenuViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_realmenu_item,parent,false)
        )
    }

    override fun onBindViewHolder(holder: SideMenuViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return sidemenulist.size
    }

    class SideMenuViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
//        init {
//            itemView.setOnClickListener {
//                val intent = Intent(itemView.context, GoSideOrderPage::class.java)
//                intent.putExtra("side_menu_img",itemView.test_menu_image.toString())
//                intent.putExtra("side_menu_name",itemView.test_menu_name.text)
//                intent.putExtra("side_menu_price",itemView.test_menu_price.text)
//                itemView.context.startActivity(intent)
//            }
//        }



        private val sidemenuImg : ImageView = itemView.test_menu_image


        fun bind(sidemeunuitem: SidemenuItem){

            Glide.with(itemView).load(sidemeunuitem.sidemenuimg).into(sidemenuImg)
            itemView.test_menu_name.text = sidemeunuitem.sidemenuname
            itemView.test_menu_price.text = sidemeunuitem.sidemenuprice

        }


    }


    fun submitList(list:ArrayList<SideMenulist>){
        sidemenulist = list
        notifyDataSetChanged()
    }


}