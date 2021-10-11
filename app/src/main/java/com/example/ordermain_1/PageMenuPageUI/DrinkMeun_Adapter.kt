package com.example.ordermain_1.PageMenuPageUI

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ordermain_1.PageGoOrderPage.GoDrinkOrderPage
import com.example.ordermain_1.PageGoOrderPage.GoOrderPage
import com.example.ordermain_1.R
import com.example.ordermain_1.rerofit.DrinkMenulist
import kotlinx.android.synthetic.main.item_layout_drinkmenu.view.*
import kotlinx.android.synthetic.main.layout_realmenu_item.view.*

class DrinkMeun_Adapter:RecyclerView.Adapter<DrinkMeun_Adapter.DrinkMenuViewHolder>() {

    private var drinkList = ArrayList<DrinkMenulist>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkMenuViewHolder {
        return DrinkMenuViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_realmenu_item,parent,false)
        )

    }


    override fun onBindViewHolder(holder: DrinkMenuViewHolder, position: Int) {
        holder.bind(drinkList[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, GoDrinkOrderPage::class.java)

            intent.putExtra("drink_menu_img",drinkList[position].drinkmenuimage
            )
            intent.putExtra("drink_menu_name",holder.itemView.test_menu_name.text)
            intent.putExtra("drink_menu_price",holder.itemView.test_menu_price.text)

            holder.itemView.context.startActivity(intent)


        }
    }



    override fun getItemCount(): Int {
        return drinkList.size
    }

    class DrinkMenuViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){


        private val menuImg : ImageView = itemView.test_menu_image


        fun bind(drinkmeunuitem: DrinkMenulist){
            Glide.with(itemView).load(drinkmeunuitem.drinkmenuimage).into(menuImg)
            itemView.test_menu_name.text = drinkmeunuitem.drinkmenuname
            itemView.test_menu_price.text = drinkmeunuitem.drinkmenuprice
        }


    }


    fun submitList(list:ArrayList<DrinkMenulist>){
        drinkList = list
        notifyDataSetChanged()
    }

    //주석석

}