package com.example.ordermain_1.PageComOrderPage

import android.annotation.SuppressLint
import android.content.Intent
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ordermain_1.DataBaseObject.DBobject
import com.example.ordermain_1.MainActivityViewModel
import com.example.ordermain_1.PageGoOrderPage.DrinkmenuDatabase.DrinkmenuDataBase
import com.example.ordermain_1.PageGoOrderPage.DrinkmenuDatabase.DrinkmenuEntity
import com.example.ordermain_1.PageGoOrderPage.GoOrderPage
import com.example.ordermain_1.PageGoOrderPage.RealmenuDatabase.OnDeleteListener
import com.example.ordermain_1.PageGoOrderPage.RealmenuDatabase.RealmenuDataBase
import com.example.ordermain_1.PageGoOrderPage.RealmenuDatabase.RealmenuEntity
import com.example.ordermain_1.PageGoOrderPage.SidemenuDatabase.SidemenuDataBase
import com.example.ordermain_1.PageGoOrderPage.SidemenuDatabase.SidemenuEntity
import com.example.ordermain_1.PageLastYeah.LastYeah
import com.example.ordermain_1.PageLastYeah.LastYeahDatabase.LastDataBase
import com.example.ordermain_1.PageLastYeah.LastYeahDatabase.LastEntity
import com.example.ordermain_1.PageMenuPageUI.fakeComOrder
import com.example.ordermain_1.R
import com.example.ordermain_1.rerofit.MainMenulist
import com.example.ordermain_1.rerofit.Order_Menu_Item
import com.example.ordermain_1.rerofit.Retrofit_Manager
import com.example.ordermain_1.rerofit.Small_Order_Menu_Item
import kotlinx.android.synthetic.main.activity_completed_order_page.*
import kotlinx.android.synthetic.main.activity_menu_page_ui.*
import kotlinx.android.synthetic.main.item_layout_com_order.*


@SuppressLint("StaticFieldLeak")
class Completed_Order_Page : AppCompatActivity(), OnDeleteListener {

    val TAG: String ="로그"

    lateinit var realmenuList : List<RealmenuEntity>
    lateinit var sidemenuList : List<SidemenuEntity>
    lateinit var drinkmenuList : List<DrinkmenuEntity>
    lateinit var lastList : List<LastEntity>

    lateinit var mainMenulist: List<MainMenulist>

    lateinit var realdb : RealmenuDataBase
    lateinit var sidedb : SidemenuDataBase
    lateinit var drinkdb : DrinkmenuDataBase
    lateinit var lastdb : LastDataBase



    private lateinit var viewModel : MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed_order_page)

//        test()


        realdb = RealmenuDataBase.getinstance(this)!!
        sidedb = SidemenuDataBase.getinstance(this)!!
        drinkdb = DrinkmenuDataBase.getinstance(this)!!
        lastdb = LastDataBase.getinstance(this)!!

        //여기 장바구니임
        wowOrderGobtn.setOnClickListener {

            Log.d(TAG, "onCreate:")
            val menuitem = ArrayList<Small_Order_Menu_Item>()


            val menulist_id_count = Small_Order_Menu_Item(231,1)
            menuitem.add(menulist_id_count)

            Retrofit_Manager.retrofit_manger.OrderMenuPost(Order_Menu_Item(menuitem,"ssss"))
            startActivity(Intent(this,LastYeah::class.java))
        }

        realmenugetAll()
        sidemenugetAll()
        drinkmenugetAll()
        real_price_getAll()

    }

    private fun real_price_getAl2l(): Unit {
        var count_sum: Int = 0
        val realmenugetallTask=(object:AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                realmenuList=realdb.realmenuDAO().realmenugetAll()
            }


            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                for(i in realmenuList.indices)
                    count_sum += realmenuList.get(i).realmenuprice!!.toInt()

                for(i in sidemenuList.indices)
                    count_sum += sidemenuList.get(i).sidemenuprice!!.toInt()

                for(i in drinkmenuList.indices)
                    count_sum += drinkmenuList.get(i).drinkmenuprice!!.toInt()

                Log.d("합계", "$count_sum")
                com_resultprice_txt.text = count_sum.toString() + "원"
            }

        }).execute()
    }

//    private fun test(){
//        val realmenu_price = intent.getStringExtra("test_menu_pricesum")
//        Log.d(TAG, "test: $realmenu_price")
//    }

    private fun drinkmenugetAll(){
        var dirnkmenugetTask = (object :AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                drinkmenuList = drinkdb.drinkmenuDAO().drinkmenugetAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                drinkmenuSetRecyclerView()
            }
        }).execute()
    }

    private fun drinkmenuSetRecyclerView(){
        val com_DrinkMenu_Adapter = Com_DrinkMenu_Adapter(this)
        com_DrinkMenu_Adapter.submitList(drinkmenuList)

        drinkorderrecyclerview.apply{
            layoutManager = LinearLayoutManager(this@Completed_Order_Page)
            adapter = com_DrinkMenu_Adapter
        }

    }

    private fun drinkmenuDelete(drinkmenuEntity: DrinkmenuEntity){
        var drinkmenudeleteTask = (object:AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                drinkdb.drinkmenuDAO().drinkmenudelete(drinkmenuEntity)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                drinkmenugetAll()
            }

        }).execute()
    }

    private fun sidemenugetAll() {
        var sidemenugetTask =(object : AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                sidemenuList= sidedb.sidemenuDAO().sidemenugetALl()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                sidemenuSetRecyclerView()
            }

        }).execute()

    }



    private fun sidemenuSetRecyclerView() {
        val com_SideMenu_Adapter = Com_SideMenu_Adapter(this)
        com_SideMenu_Adapter.submitList(sidemenuList)

        sideorderrecyclerview.apply{
            layoutManager = LinearLayoutManager(this@Completed_Order_Page)
            adapter = com_SideMenu_Adapter
        }
    }

    private fun sidemenuDelete(sidemenuEntity: SidemenuEntity){
        var sidemenudeleteTask =(object:AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                sidedb.sidemenuDAO().sidemenuDelete(sidemenuEntity)
            }
            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                sidemenugetAll()
            }

        }).execute()
    }

    private fun realmenugetAll() {
        val realmenugetallTask=(object:AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                realmenuList=realdb.realmenuDAO().realmenugetAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                realmenuSetRecyclerView()
            }

        }).execute()
    }

    private fun realmenuSetRecyclerView(){
        val com_RealMenu_Adapter =Com_RealMenu_Adapter(this)
        com_RealMenu_Adapter.submitList(realmenuList)

        realmenurecyclerview.apply {
            layoutManager = LinearLayoutManager(this@Completed_Order_Page)
            adapter = com_RealMenu_Adapter
        }

    }

    private fun realmenuDelete(realmenuEntity: RealmenuEntity){
        val realmenuDeleteTask=(object:AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                realdb.realmenuDAO().realmenuDelete(realmenuEntity)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                realmenugetAll()
            }


        }).execute()
    }

    private fun lastinsert(lastEntity: LastEntity){
        val lastinsertTask = (object : AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                lastdb.lastDAO().lastinsert(lastEntity)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                lastdb.lastDAO().lastgetAll()
            }
        })
    }



    override fun onrealmenuDeleteListner(realmenuEntity: RealmenuEntity) {
        realmenuDelete(realmenuEntity)
    }

    override fun onsidemenuDeleteListener(sidemenuEntity: SidemenuEntity) {
       sidemenuDelete(sidemenuEntity)
    }

    override fun ondrinkmenuDeleteListener(drinkmenuEntity: DrinkmenuEntity) {
       drinkmenuDelete(drinkmenuEntity)
    }


    private fun real_price_getAll(): Unit {
        var count_sum: Int = 0
        val realmenugetallTask=(object:AsyncTask<Unit,Unit,Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                realmenuList=realdb.realmenuDAO().realmenugetAll()
            }


            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                for(i in realmenuList.indices)
                    count_sum += realmenuList.get(i).realmenuprice!!.toInt()

                for(i in sidemenuList.indices)
                    count_sum += sidemenuList.get(i).sidemenuprice!!.toInt()

                for(i in drinkmenuList.indices)
                    count_sum += drinkmenuList.get(i).drinkmenuprice!!.toInt()

                Log.d("합계", "$count_sum")
                com_resultprice_txt.text = count_sum.toString() + "원"
            }

        }).execute()
    }


}