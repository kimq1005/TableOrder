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
import kotlinx.android.synthetic.main.layout_realmenu_item.view.*

class DrinkMeun_Adapter:RecyclerView.Adapter<DrinkMeun_Adapter.DrinkMenuViewHolder>() {

    private var drinkmenuItemList : List<DrinkmenuItem>?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkMenuViewHolder {
        return DrinkMenuViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.layout_realmenu_item,parent,false)
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
                intent.putExtra("drink_menu_img",itemView.test_menu_image.toString())
                intent.putExtra("drink_menu_name",itemView.test_menu_name.text)
                intent.putExtra("drink_menu_price",itemView.test_menu_price.text)
                itemView.context.startActivity(intent)
            }
        }

        private val menuImg : ImageView = itemView.test_menu_image
        //


        fun bind(drinkmeunuitem: DrinkmenuItem){
            Glide.with(itemView).load(drinkmeunuitem.drinkmenuimg).into(menuImg)
            itemView.test_menu_name.text = drinkmeunuitem.drinkmenuname
//            itemView.tes.text = drinkmeunuitem.drinkmenuinformation
            itemView.test_menu_price.text = drinkmeunuitem.drinkmenuprice.toString()
        }


    }


    fun submitList(list:List<DrinkmenuItem>?){
        drinkmenuItemList = list
        notifyDataSetChanged()
    }

    //주석석

}