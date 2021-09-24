package com.example.ordermain_1.PageMenuPageUI


import android.content.Intent
import android.os.Bundle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ordermain_1.App

import com.example.ordermain_1.R
import com.example.ordermain_1.PageGoOrderPage.GoOrderPage
import com.example.ordermain_1.rerofit.retrofitItem
import kotlinx.android.synthetic.main.item_layout_realmenu.view.*
import kotlinx.android.synthetic.main.layout_realmenu_item.view.*


class RealMenu_Adapter:RecyclerView.Adapter<RealMenu_Adapter.RealMenuViewHolder>() {

//    private var realmenuItemList : List<RealMenuItem>?=null
    private var menuList  = ArrayList<retrofitItem>()



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RealMenuViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.layout_realmenu_item,parent,false)
        return RealMenuViewHolder(view)


    }

    override fun onBindViewHolder(holder: RealMenuViewHolder, position: Int) {
//        menuList?.let{
//            holder.bind(it[position])
//        }
        holder.bind(menuList[position])

    }


    override fun getItemCount(): Int {
//        return menuList?.size ?:0
        return menuList.size
    }

    class RealMenuViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        init{
            itemView.setOnClickListener {
                val realmenulist = ArrayList<RealMenuItem>()
//                val url = itemView.tag as? String ?: return@setOnClickListener

                val intent = Intent(itemView.context, GoOrderPage::class.java)
//                intent.putExtra("menu_img", url)


//                bundle.putSerializable("realmenu_list",realmenulist)
//                intent.putExtra("array_bundle",bundle)
                intent.putExtra("menu_img",itemView.test_menu_image.toString())
                intent.putExtra("menu_name",itemView.test_menu_name.text)
                intent.putExtra("menu_price",itemView.test_menu_price.text)
                itemView.context.startActivity(intent)
            }
        }


//       private val menuImg : ImageView = itemView.real_menu_img
        private val menuImg : ImageView = itemView.test_menu_image


//        fun bind(realmeunuitem: RealMenuItem){
//
//            Glide.with(itemView).load(realmeunuitem.realmenuimg).into(menuImg)
//            itemView.real_menuname_txt.text = realmeunuitem.realmenuname
//            itemView.real_menuinformation_txt.text = realmeunuitem.realmenuinformation
//            itemView.real_menuprice_txt.text = realmeunuitem.realmenuprice.toString()
//
//        }

        fun bind(realmeunuitem: retrofitItem){

            Glide.with(itemView).load(realmeunuitem.menuimage).into(menuImg)
            itemView.test_menu_name.text = realmeunuitem.menuname
//            itemView.test_menu_information.text = realmeunuitem.realmenuinformation
            itemView.test_menu_price.text = realmeunuitem.menuprice


        }

    }


    fun submitList(list:ArrayList<retrofitItem>){
        menuList = list
        notifyDataSetChanged()
    }







}