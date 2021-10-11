package com.example.ordermain_1.PageMenuPageUI


import android.content.Intent
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ordermain_1.App

import com.example.ordermain_1.R
import com.example.ordermain_1.PageGoOrderPage.GoOrderPage
import com.example.ordermain_1.rerofit.MainMenulist
import com.example.ordermain_1.rerofit.retrofitItem
import com.example.ordermain_1.rerofit.util.Log.TAG
import kotlinx.android.synthetic.main.item_layout_realmenu.view.*
import kotlinx.android.synthetic.main.layout_realmenu_item.view.*


class RealMenu_Adapter:RecyclerView.Adapter<RealMenu_Adapter.RealMenuViewHolder>() {

//    private var realmenuItemList : List<RealMenuItem>?=null
    private var menuList  = ArrayList<MainMenulist>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RealMenuViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_realmenu_item,parent,false)
        return RealMenuViewHolder(view)


    }

    override fun onBindViewHolder(holder: RealMenuViewHolder, position: Int) {

        holder.bind(menuList[position])
        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, GoOrderPage::class.java)

            intent.putExtra("menu_img",menuList[position].menuimage)
            intent.putExtra("menu_name",holder.itemView.test_menu_name.text)
            intent.putExtra("menu_price",holder.itemView.test_menu_price.text)
            intent.putExtra("menu_id",menuList[position].id.toString())


            holder.itemView.context.startActivity(intent)


        }



    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    class RealMenuViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        private val menuImg : ImageView = itemView.test_menu_image
        fun bind(realmeunuitem: MainMenulist){
            Glide.with(itemView).load(realmeunuitem.menuimage).into(menuImg)
            itemView.test_menu_name.text = realmeunuitem.menuname
            itemView.test_menu_price.text = realmeunuitem.menuprice
        }

    }


    fun submitList(list:ArrayList<MainMenulist>){
        menuList = list
        notifyDataSetChanged()
    }







}