package com.example.ordermain_1.PageLastYeah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.ordermain_1.R
import com.example.ordermain_1.rerofit.Retrofit_Manager
import kotlinx.android.synthetic.main.activity_completed_order_page.*
import kotlinx.android.synthetic.main.activity_last_yeah.*

class LastYeah : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_last_yeah)

//        val drink_menu_name = intent.getStringExtra("drink_menu_name")
//        val drink_menu_price = intent.getStringExtra("drink_menu_price")
//        val drink_menu_img = getIntent().getStringExtra("drink_menu_img")

        val menu_request = intent.getStringExtra("request")
        val menu_priceresult = intent.getStringExtra("resultprice")

        last_menuprice_result.text = menu_priceresult.toString()
        last_request.text = menu_request.toString()


        OrderCancle.setOnClickListener {

            Retrofit_Manager.retrofit_manger.WebLoginPost()

            val lastpriceresult = last_menuprice_result.text.toString()
            val intent = Intent(this,OrderCanclePage::class.java)
            intent.putExtra("lastresultprice",lastpriceresult)


            startActivity(intent)









        }
    }
}