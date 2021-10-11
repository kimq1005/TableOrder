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
import com.example.ordermain_1.PageMainActivity.test_mai_value
import com.example.ordermain_1.R
import com.example.ordermain_1.rerofit.MainMenulist
import kotlinx.android.synthetic.main.activity_go_order_page.*
import kotlinx.android.synthetic.main.activity_go_order_page.back_icon


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

        back_icon.setOnClickListener {
            onBackPressed()
        }

        real_orderlist_icon.setOnClickListener {
            val intent = Intent(this,Completed_Order_Page::class.java)
            startActivity(intent)
        }


        GO_Complted_Page.setOnClickListener { //저장된거야 이건

            val realmenu = RealmenuEntity(

                null,
                OrderPage_realmenu_id.text.toString(),
                OrderPage_menuName_txt.text.toString(),
                OrderPage_resultPrice_txt.text.toString(),
                ZeroBtn.text.toString() //count
            )

            realmenuInsert(realmenu)

//            GoCompleteActivity_with_Sum()
            Toast.makeText(this,"장바구니 담기 완료",Toast.LENGTH_SHORT).show()
            Log.d(TAG, "하이요: ${OrderPage_realmenu_id.text}")
            Log.d(TAG, "realmenu 데이터베이스 저장 완료 ${OrderPage_menuName_txt.text}")

            test_mai_value.check_sum = OrderPage_resultPrice_txt.text.toString().toInt()

        }

        menuBtnClick()


    }

    private fun GoCompleteActivity_with_Sum() {


        val pricewowsum = OrderPage_resultPrice_txt.text
        val intent = Intent(this,Completed_Order_Page::class.java)
        intent.putExtra("test_menu_pricesum",pricewowsum)
        startActivity(intent)

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
        val menu_img = intent.getStringExtra("menu_img")
        val menu_id = intent.getStringExtra("menu_id")
        

        Glide.with(this).load(menu_img).into(OrderPage_menuImg_img)

        OrderPage_menuName_txt.text= menu_name.toString()
        OrderPage_menuPrice_txt.text  = menu_price.toString()
        OrderPage_resultPrice_txt.text = menu_price.toString()
        OrderPage_realmenu_id.text = menu_id.toString()





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