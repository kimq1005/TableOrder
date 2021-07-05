package com.example.ordermain_1.PageDR

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
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

        var list = mutableListOf<String>()

        OrderPage_menuName_txt.text= menu_name
        OrderPage_menuPrice_txt.text  = menu_price

        var holymoly = OrderPage_menuPrice_txt.text
//        OrderPage_resultPrice_txt.text = holymoly

        list.add(holymoly.toString())
        var list_1 = list[0]


        list_1 = list_1.replace(",","")
        list_1 = list_1.replace("억","")
        resultprice = list_1.toInt()



        menuBtnClick()


    }

    private fun menuBtnClick() {


        var sum:Int = 0
        PlusBtn.setOnClickListener {
            for(i in 1..1){
                 sum=sum+1
            }

             ZeroBtn.text = sum.toString()

            if(sum>0){
                resultprice= resultprice * sum
                OrderPage_resultPrice_txt.text = resultprice.toString()
            }


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