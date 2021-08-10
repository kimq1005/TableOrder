package com.example.ordermain_1.PageMenuPageUI

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ordermain_1.PageGoOrderPage.GoDrinkOrderPage
import com.example.ordermain_1.R
import kotlinx.android.synthetic.main.item_layout_drinkmenu.view.*

class DrinkMeun_Adapter:RecyclerView.Adapter<DrinkMeun_Adapter.DrinkMenuViewHolder>() {

    private var drinkmenuItemList : List<DrinkmenuItem>?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkMenuViewHolder {
        return DrinkMenuViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_layout_drinkmenu,parent,false)
        )

    }


    override fun onBindViewHolder(holder: DrinkMenuViewHolder, position: Int) {
        drinkmenuItemList?.let{
            holder.bind(it[position])
        }
    }



    override fun getItemCount(): Int {
        return drinkmenuItemList?.size ?:0
    }

    class DrinkMenuViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){

        init {
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, GoDrinkOrderPage::class.java)
                intent.putExtra("drink_menu_img",itemView.drink_menu_img.toString())
                intent.putExtra("drink_menu_name",itemView.drink_menuname_txt.text)
                intent.putExtra("drink_menu_price",itemView.drink_menuprice_txt.text)
                itemView.context.startActivity(intent)
            }
        }

        private val menuImg : ImageView = itemView.drink_menu_img
        //


        fun bind(drinkmeunuitem: DrinkmenuItem){
            Glide.with(itemView).load(drinkmeunuitem.drinkmenuimg).into(menuImg)
            itemView.drink_menuname_txt.text = drinkmeunuitem.drinkmenuname
            itemView.drink_menuinformation_txt.text = drinkmeunuitem.drinkmenuinformation
            itemView.drink_menuprice_txt.text = drinkmeunuitem.drinkmenuprice.toString()
        }


    }


    fun submitList(list:List<DrinkmenuItem>?){
        drinkmenuItemList = list
        notifyDataSetChanged()
    }

    //주석석

}