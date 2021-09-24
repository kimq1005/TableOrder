package com.example.ordermain_1.PageGoOrderPage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.ordermain_1.DataBaseObject.DBobject
import com.example.ordermain_1.DataBaseObject.MyTestSum
import com.example.ordermain_1.PageComOrderPage.Completed_Order_Page
import com.example.ordermain_1.PageGoOrderPage.RealmenuDatabase.RealmenuDataBase
import com.example.ordermain_1.PageGoOrderPage.RealmenuDatabase.RealmenuEntity
import com.example.ordermain_1.R
import kotlinx.android.synthetic.main.activity_completed_order_page.*
import kotlinx.android.synthetic.main.activity_go_order_page.*


@SuppressLint("StaticFieldLeak")
class GoOrderPage : AppCompatActivity() {

    val TAG : String = "로그"
    lateinit var realdb: RealmenuDataBase
//    lateinit var myTestSum: ArrayList<MyTestSum>


    var realmenuList = listOf<RealmenuEntity>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_go_order_page)


        realdb = RealmenuDataBase.getinstance(this)!!
        realmenucall()



        GO_Complted_Page.setOnClickListener {   //저장된거야 이건
            val realmenu = RealmenuEntity(null,OrderPage_menuName_txt.text.toString(),OrderPage_resultPrice_txt.text.toString(),ZeroBtn.text.toString())
//            DBobject.mysum.add(OrderPage_resultPrice_txt.toString())
            realmenuInsert(realmenu)
//            Toast.makeText(this,"${DBobject.mysum}",Toast.LENGTH_SHORT).show()

            Log.d(TAG, "realmenu 데이터베이스 저장 완료 ${OrderPage_menuName_txt.text}")
        }

        menuBtnClick()

    }

    fun realmenucall() {

//        lateinit var menu_name:String
//        lateinit var menu_price:String


//        if( intent.getStringExtra("menu_name")!=null){
//            menu_name = intent.getStringExtra("menu_name").toString()
//        }
//
//        if( intent.getStringExtra("menu_price")!=null){
//            menu_price = intent.getStringExtra("menu_price").toString()
//        }
        val menu_name = intent.getStringExtra("menu_name")
        val menu_price = intent.getStringExtra("menu_price")
        Log.d(TAG, "realmenucall: ${menu_name.toString()} ")

        val menu_img = getIntent().getStringExtra("menu_img")
        Glide.with(this).load(menu_img).into(OrderPage_menuImg_img)
        OrderPage_menuName_txt.text= menu_name.toString()
        OrderPage_menuPrice_txt.text  = menu_price.toString()
        OrderPage_resultPrice_txt.text = menu_price

    }


    fun menuBtnClick() {

        val price = OrderPage_menuPrice_txt.text
        val Stringprice = price.toString()
        val Intprice = Stringprice.toInt()


        var sum:Int = 0

        PlusBtn.setOnClickListener {
            for(i in 1..1){
                 sum=sum+1
            }

            ZeroBtn.text = sum.toString()
            OrderPage_resultPrice_txt.text = (sum* Intprice).toString()

        }


        MinusBtn.setOnClickListener {
            for(i in 1..1){
                sum=sum-1
            }

            if(sum<0){
                sum = 0
            }

            ZeroBtn.text = sum.toString()
            OrderPage_resultPrice_txt.text = (sum*Intprice).toString()


        }


    }

    fun realmenuInsert(realmenuEntity: RealmenuEntity){
        val realmenuinsertTask = (object: AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                realdb.realmenuDAO().realmenuInsert(realmenuEntity)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                realmenugetAll()
            }

        }).execute()

    }

    private fun realmenugetAll(){
        val realmenugetTask = (object : AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                realmenuList = realdb.realmenuDAO().realmenugetAll()
            }

        }).execute()
    }



}