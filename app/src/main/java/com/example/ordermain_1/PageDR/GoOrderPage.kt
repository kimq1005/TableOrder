package com.example.ordermain_1.PageDR

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.ordermain_1.Item.RealMenuItem
import com.example.ordermain_1.R
import kotlinx.android.synthetic.main.activity_go_order_page.*

class GoOrderPage : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_order_page)

        val menu_name = intent.getStringExtra("menu_name")
        val menu_info = intent.getStringExtra("menu_information")
        val menu_price = intent.getStringExtra("menu_price")
//        val menu_img = intent.getStringExtra("menu_img")
        //



//        Glide.with(this).load(menu_img).into(menu_img)
        OrderPage_menuName_txt.text= menu_name
        OrderPage_menuinfo_txt.text =menu_info
        OrderPage_menuPrice_txt.text  = menu_price

        menuBtnClick()








    }

    private fun menuBtnClick() {

        var sum:Int = 0

         PlusBtn.setOnClickListener {
             for(i in 1..1){
                 sum=sum+1
         }

             ZeroBtn.text = sum.toString()

        }

        MinusBtn.setOnClickListener {
            for(i in 1..1){
                sum=sum-1
            }

            ZeroBtn.text = sum.toString()

            if(sum<0){
                sum = 0
                ZeroBtn.text = sum.toString()
            }
        }

    }
}