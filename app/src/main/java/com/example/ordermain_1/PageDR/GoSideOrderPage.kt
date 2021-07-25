package com.example.ordermain_1.PageDR

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.ordermain_1.R
import kotlinx.android.synthetic.main.activity_go_order_page.*
import kotlinx.android.synthetic.main.activity_go_order_page.GO_Complted_Page
import kotlinx.android.synthetic.main.activity_go_order_page.MinusBtn
import kotlinx.android.synthetic.main.activity_go_order_page.OrderPage_menuPrice_txt
import kotlinx.android.synthetic.main.activity_go_order_page.PlusBtn
import kotlinx.android.synthetic.main.activity_go_order_page.ZeroBtn
import kotlinx.android.synthetic.main.activity_go_side_order_page.*

class GoSideOrderPage : AppCompatActivity() {

    private var resultprice: Int= 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_side_order_page)


        sidemenucall()


        var holymoly = Side_OrderPage_menuPrice_txt.text
        var list = mutableListOf<String>()


        list.add(holymoly.toString())
        var list_1 = list[0]


        list_1 = list_1.replace(",","")
        list_1 = list_1.replace("억","")
        resultprice = list_1.toInt()

        GO_Complted_Page.setOnClickListener {
            val intent = Intent(this,Completed_Order_Page::class.java)
            startActivity(intent)

        }

        menuBtnClick()

    }

    private fun sidemenucall() {
        val side_menu_name = intent.getStringExtra("side_menu_name")
        val side_menu_price = intent.getStringExtra("side_menu_price")
        val side_menu_img = getIntent().getStringExtra("side_menu_img")

        Glide.with(this).load(side_menu_img).into(Side_OrderPage_menuImg_img)
        Side_OrderPage_menuName_txt.text= side_menu_name
        Side_OrderPage_menuPrice_txt.text  = side_menu_price
        Side_OrderPage_resultPrice_txt.text = side_menu_price
    }

    private fun menuBtnClick() {

        var sum:Int = 0
        var result_sum:String? =null
        PlusBtn.setOnClickListener {
            for(i in 1..1){
                sum=sum+1
            }

            ZeroBtn.text = sum.toString()
            Side_OrderPage_resultPrice_txt.text = (sum*resultprice).toString()

        }



        MinusBtn.setOnClickListener {
            for(i in 1..1){
                sum=sum-1
            }

            ZeroBtn.text = sum.toString()
            Side_OrderPage_resultPrice_txt.text = (sum*resultprice).toString()


            if(sum<0){
                sum = 0
                ZeroBtn.text = sum.toString()

            }


        }





    }



}