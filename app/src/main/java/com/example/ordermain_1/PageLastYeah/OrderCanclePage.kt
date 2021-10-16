package com.example.ordermain_1.PageLastYeah

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ordermain_1.PageMenuPageUI.MenuPageUI
import com.example.ordermain_1.R
import kotlinx.android.synthetic.main.activity_last_yeah.*
import kotlinx.android.synthetic.main.activity_last_yeah.last_menuprice_result
import kotlinx.android.synthetic.main.activity_order_cancle_page.*

class OrderCanclePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_cancle_page)




        val Cancel_menu_priceresult = intent.getStringExtra("lastresultprice")
        last_menuprice_result.text = Cancel_menu_priceresult.toString()


        cancel_go_home.setOnClickListener {
            val intent = Intent(this,MenuPageUI::class.java)
            startActivity(intent)
        }

    }
}