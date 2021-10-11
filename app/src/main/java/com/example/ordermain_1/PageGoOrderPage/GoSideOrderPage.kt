package com.example.ordermain_1.PageGoOrderPage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.ordermain_1.PageComOrderPage.Completed_Order_Page
import com.example.ordermain_1.PageGoOrderPage.RealmenuDatabase.RealmenuDataBase
import com.example.ordermain_1.PageGoOrderPage.RealmenuDatabase.RealmenuEntity
import com.example.ordermain_1.PageGoOrderPage.SidemenuDatabase.SidemenuDataBase
import com.example.ordermain_1.PageGoOrderPage.SidemenuDatabase.SidemenuEntity
import com.example.ordermain_1.R
import kotlinx.android.synthetic.main.activity_go_drink_order_page.*
import kotlinx.android.synthetic.main.activity_go_order_page.*
import kotlinx.android.synthetic.main.activity_go_order_page.MinusBtn
import kotlinx.android.synthetic.main.activity_go_order_page.PlusBtn
import kotlinx.android.synthetic.main.activity_go_order_page.ZeroBtn
import kotlinx.android.synthetic.main.activity_go_side_order_page.*

@SuppressLint("StaticFieldLeak")
class GoSideOrderPage : AppCompatActivity() {

    var TAG : String ="로그"

    lateinit var sidedb: SidemenuDataBase
    var sidemenuList = listOf<SidemenuEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_side_order_page)

        side_back_icon.setOnClickListener {
            onBackPressed()
        }

        side_orderlist_icon.setOnClickListener {
            val intent = Intent(this,Completed_Order_Page::class.java)
            startActivity(intent)
        }

        sidedb = SidemenuDataBase.getinstance(this)!!


        sidemenucall()
        menuBtnClick()

        GO_SideComplted_Page.setOnClickListener {//
            val sidemenu = SidemenuEntity(
                null,
                Side_OrderPage_realmenu_id.text.toString(),
                Side_OrderPage_menuName_txt.text.toString(),
                Side_OrderPage_resultPrice_txt.text.toString(),
                SideZeroBtn.text.toString()//count
            )

            sidemenuinsert(sidemenu)
            Toast.makeText(this,"장바구니 담기 완료",Toast.LENGTH_SHORT).show()
            Log.d(TAG, "사이드메뉴 저장 로그다")

        }

    }

    private fun sidemenucall() {
        val side_menu_name = intent.getStringExtra("side_menu_name")
        val side_menu_price = intent.getStringExtra("side_menu_price")
        val side_menu_img = getIntent().getStringExtra("side_menu_img")
        val side_menu_id = intent.getStringExtra("side_menu_id")

        Glide.with(this).load(side_menu_img).into(Side_OrderPage_menuImg_img)
        Side_OrderPage_menuName_txt.text= side_menu_name
        Side_OrderPage_menuPrice_txt.text  = side_menu_price
        Side_OrderPage_resultPrice_txt.text = side_menu_price
        Side_OrderPage_realmenu_id.text = side_menu_id.toString()


    }

    private fun menuBtnClick() {

        val price = Side_OrderPage_menuPrice_txt.text
        val Stringprice = price.toString()
        val Intprice = Stringprice.toInt()

        var sum:Int = 0
        PlusBtn.setOnClickListener {
            for(i in 1..1){
                sum=sum+1
            }

            SideZeroBtn.text = sum.toString()
            Side_OrderPage_resultPrice_txt.text = (sum*Intprice).toString()

        }


        MinusBtn.setOnClickListener {
            for(i in 1..1){
                sum=sum-1
            }

            SideZeroBtn.text = sum.toString()
            Side_OrderPage_resultPrice_txt.text = (sum*Intprice).toString()


            if(sum<0){
                sum = 0

            }


        }





    }

    private fun sidemenuinsert(sidemenuEntity: SidemenuEntity){
        var sidemenuinsertTask = (object : AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                sidedb.sidemenuDAO().sidemenuinsert(sidemenuEntity)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                sidemenugetAll()
            }

        }).execute()

    }

    private fun sidemenugetAll() {
        var sidemenugetTask =(object : AsyncTask<Unit, Unit, Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                sidemenuList= sidedb.sidemenuDAO().sidemenugetALl()
            }

        }).execute()

    }



}