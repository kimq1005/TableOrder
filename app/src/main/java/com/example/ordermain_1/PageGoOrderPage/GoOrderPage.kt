package com.example.ordermain_1.PageGoOrderPage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.ordermain_1.PageComOrderPage.Completed_Order_Page
import com.example.ordermain_1.R
import kotlinx.android.synthetic.main.activity_go_order_page.*

class GoOrderPage : AppCompatActivity() {


    private var resultprice: Int= 0

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_order_page)



        realmenucall()


        var holymoly = OrderPage_menuPrice_txt.text
        var list = mutableListOf<String>()



        list.add(holymoly.toString())
        var list_1 = list[0]


        list_1 = list_1.replace(",","")
        list_1 = list_1.replace("억","")
        resultprice = list_1.toInt()

        GO_Complted_Page.setOnClickListener {
            val intent = Intent(this, Completed_Order_Page::class.java)
            startActivity(intent)
        }

        menuBtnClick()

    }

    private fun realmenucall() {
        val menu_name = intent.getStringExtra("menu_name")
        val menu_price = intent.getStringExtra("menu_price")
        val menu_img = getIntent().getStringExtra("menu_img")

        Glide.with(this).load(menu_img).into(OrderPage_menuImg_img)
        OrderPage_menuName_txt.text= menu_name
        OrderPage_menuPrice_txt.text  = menu_price
        OrderPage_resultPrice_txt.text = menu_price
    }

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