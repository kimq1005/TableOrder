package com.example.ordermain_1.PageMenuPageUI


import android.content.Intent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.example.ordermain_1.R
import com.example.ordermain_1.PageGoOrderPage.GoOrderPage
import kotlinx.android.synthetic.main.item_layout_realmenu.view.*



class RealMenu_Adapter:RecyclerView.Adapter<RealMenu_Adapter.RealMenuViewHolder>() {

    private var realmenuItemList : List<RealMenuItem>?=null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RealMenuViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout_realmenu,parent,false)
        return RealMenuViewHolder(view)



    }

    override fun onBindViewHolder(holder: RealMenuViewHolder, position: Int) {
        realmenuItemList?.let{
            holder.bind(it[position])
        }


    }


    override fun getItemCount(): Int {
        return realmenuItemList?.size ?:0
    }

    class RealMenuViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView){

        init{
            itemView.setOnClickListener {
//                val url = itemView.tag as? String ?: return@setOnClickListener

                val intent = Intent(itemView.context, GoOrderPage::class.java)
//                intent.putExtra("menu_img", url)
                intent.putExtra("menu_img",itemView.real_menu_img.toString())
                intent.putExtra("menu_name",itemView.real_menuname_txt.text)
                intent.putExtra("menu_price",itemView.real_menuprice_txt.text)
                itemView.context.startActivity(intent)

            }

        }


       private val menuImg : ImageView = itemView.real_menu_img


        fun bind(realmeunuitem: RealMenuItem){

            Glide.with(itemView).load(realmeunuitem.realmenuimg).into(menuImg)
            itemView.real_menuname_txt.text = realmeunuitem.realmenuname
            itemView.real_menuinformation_txt.text = realmeunuitem.realmenuinformation
            itemView.real_menuprice_txt.text = realmeunuitem.realmenuprice.toString()

        }

    }


    fun submitList(list:List<RealMenuItem>?){
        realmenuItemList = list
        notifyDataSetChanged()
    }







}