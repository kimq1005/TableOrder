package com.example.ordermain_1.PageGoOrderPage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.ordermain_1.PageComOrderPage.Completed_Order_Page
import com.example.ordermain_1.PageGoOrderPage.DrinkmenuDatabase.DrinkmenuDataBase
import com.example.ordermain_1.PageGoOrderPage.DrinkmenuDatabase.DrinkmenuEntity
import com.example.ordermain_1.R
import kotlinx.android.synthetic.main.activity_go_drink_order_page.*
import kotlinx.android.synthetic.main.activity_go_order_page.*
import kotlinx.android.synthetic.main.activity_go_side_order_page.MinusBtn
import kotlinx.android.synthetic.main.activity_go_side_order_page.PlusBtn
import kotlinx.android.synthetic.main.activity_go_side_order_page.ZeroBtn

@SuppressLint("StaticFieldLeak")
class GoDrinkOrderPage : AppCompatActivity() {

    var TAG :String ="로그"

    private var resultprice: Int= 0
    lateinit var drinkdb :DrinkmenuDataBase
    var drinkmenuList = listOf<DrinkmenuEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_drink_order_page)

        drinkdb = DrinkmenuDataBase.getinstance(this)!!

        drinkmenucall()


        var holymoly = Drink_OrderPage_menuPrice_txt.text
        var list = mutableListOf<String>()



        list.add(holymoly.toString())
        var list_1 = list[0]


        list_1 = list_1.replace(",","")
        list_1 = list_1.replace("억","")
        resultprice = list_1.toInt()

        GO_Drink_Complted_Page.setOnClickListener {
            var drinkmenu = DrinkmenuEntity(null,Drink_OrderPage_menuName_txt.text.toString(),Drink_OrderPage_resultPrice_txt.text.toString())
            drinkmenuinsert(drinkmenu)

            Log.d(TAG, "sidemenu 데이터베이스 저장 완료 ${Drink_OrderPage_menuName_txt.text} ")
        }



        menuBtnClick()

    }

    private fun drinkmenucall() {
        val drink_menu_name = intent.getStringExtra("drink_menu_name")
        val drink_menu_price = intent.getStringExtra("drink_menu_price")
        val drink_menu_img = getIntent().getStringExtra("drink_menu_img")

        Glide.with(this).load(drink_menu_img).into(Drink_OrderPage_menuImg_img)
        Drink_OrderPage_menuName_txt.text= drink_menu_name
        Drink_OrderPage_menuPrice_txt.text  = drink_menu_price
        Drink_OrderPage_resultPrice_txt.text = drink_menu_price
    }

    private fun menuBtnClick() {

        var sum:Int = 0
        var result_sum:String? =null
        PlusBtn.setOnClickListener {
            for(i in 1..1){
                sum=sum+1
            }

            ZeroBtn.text = sum.toString()
            Drink_OrderPage_resultPrice_txt.text = (sum*resultprice).toString()

        }



        MinusBtn.setOnClickListener {
            for(i in 1..1){
                sum=sum-1
            }

            ZeroBtn.text = sum.toString()
            Drink_OrderPage_resultPrice_txt.text = (sum*resultprice).toString()


            if(sum<0){
                sum = 0
                ZeroBtn.text = sum.toString()

            }


        }





    }

    private fun drinkmenuinsert(drinkmenuEntity: DrinkmenuEntity){
       var dirnkmenuinsertTask = (object: AsyncTask<Unit,Unit,Unit>(){
           override fun doInBackground(vararg params: Unit?) {
               drinkdb.drinkmenuDAO().drinkmenuinsert(drinkmenuEntity)
           }

           override fun onPostExecute(result: Unit?) {
               super.onPostExecute(result)
               drinkmenugetAll()
           }

       }).execute()
    }

    private fun drinkmenugetAll(){
        var dirnkmenugetTask = (object :AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                drinkmenuList = drinkdb.drinkmenuDAO().drinkmenugetAll()
            }
        }).execute()
    }



}