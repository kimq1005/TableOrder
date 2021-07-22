package com.example.ordermain_1.PageDR

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.ordermain_1.Adapter.ComOrder_Adapter
import com.example.ordermain_1.Item.ComOrderItem
import com.example.ordermain_1.Item.RealMenuItem
import com.example.ordermain_1.R
import kotlinx.android.synthetic.main.activity_go_order_page.*
import kotlinx.android.synthetic.main.item_layout_menuinformation.*

class GoOrderPage : AppCompatActivity() {


    private var resultprice: Int= 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_order_page)


        val menu_name = intent.getStringExtra("menu_name")
        val menu_price = intent.getStringExtra("menu_price")
        val menu_img = getIntent().getStringExtra("menu_img")
        var list = mutableListOf<String>()
        Glide.with(this).load(menu_img).into(OrderPage_menuImg_img)
        OrderPage_menuName_txt.text= menu_name
        OrderPage_menuPrice_txt.text  = menu_price
        OrderPage_resultPrice_txt.text = menu_price



        var holymoly = OrderPage_menuPrice_txt.text
//        OrderPage_resultPrice_txt.text = holymoly

        list.add(holymoly.toString())
        var list_1 = list[0]


        list_1 = list_1.replace(",","")
        list_1 = list_1.replace("억","")
        resultprice = list_1.toInt()

        GO_Complted_Page.setOnClickListener {

            val intent = Intent(this,Completed_Order_Page::class.java)
            val menu_name = intent.getStringExtra("menu_name")
            val menu_price = intent.getStringExtra("menu_price")

            intent.putExtra("wowmenuname",menu_name)
            intent.putExtra("wowmenuprice",menu_price)
//            SpendmenuData()


        }


        menuBtnClick()

    }


//    private fun SpendmenuData() {
//        val intent = Intent(this,Completed_Order_Page::class.java)
//        val menu_name = intent.getStringExtra("menu_name")
//        val menu_price = intent.getStringExtra("menu_price")
//
//        intent.putExtra("wowmenuname",menu_name)
//        intent.putExtra("wowmenuprice",menu_price)
//
//    }


    private fun menuBtnClick() {

        var sum:Int = 0
        var result_sum:String? =null
        PlusBtn.setOnClickListener {
            for(i in 1..1){
                 sum=sum+1
            }

            ZeroBtn.text = sum.toString()
            OrderPage_resultPrice_txt.text = (sum*resultprice).toString()

        }



        MinusBtn.setOnClickListener {
            for(i in 1..1){
                sum=sum-1
            }

            ZeroBtn.text = sum.toString()
            OrderPage_resultPrice_txt.text = (sum*resultprice).toString()


            if(sum<0){
                sum = 0
                ZeroBtn.text = sum.toString()

            }


        }











    }



}