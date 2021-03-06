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
import com.example.ordermain_1.rerofit.*
import kotlinx.android.synthetic.main.activity_completed_order_page.*
import kotlinx.android.synthetic.main.activity_menu_page_ui.*
import kotlinx.android.synthetic.main.item_layout_com_order.*


@SuppressLint("StaticFieldLeak")
class Completed_Order_Page : AppCompatActivity(), OnDeleteListener {

    val TAG: String = "로그"


    lateinit var realmenuList: List<RealmenuEntity>
    lateinit var sidemenuList: List<SidemenuEntity>
    lateinit var drinkmenuList: List<DrinkmenuEntity>


    lateinit var realdb: RealmenuDataBase
    lateinit var sidedb: SidemenuDataBase
    lateinit var drinkdb: DrinkmenuDataBase
    lateinit var lastdb: LastDataBase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed_order_page)


        realdb = RealmenuDataBase.getinstance(this)!!
        sidedb = SidemenuDataBase.getinstance(this)!!
        drinkdb = DrinkmenuDataBase.getinstance(this)!!
        lastdb = LastDataBase.getinstance(this)!!


        wowOrderGobtn.setOnClickListener {


            menutotalOrder()

            val request = requestEditText.text.toString()
            val priceresult = com_resultprice_txt.text.toString()

            Log.d(TAG, "onCreate:$request \n $priceresult")


            val intent = Intent(this, LastYeah::class.java)

            intent.putExtra("request", request)
            intent.putExtra("resultprice", priceresult)
            startActivity(intent)


        }

        realmenugetAll()
        sidemenugetAll()
        drinkmenugetAll()
        real_price_getAll()

    }


    private fun drinkmenugetAll() {
        var dirnkmenugetTask = (object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                drinkmenuList = drinkdb.drinkmenuDAO().drinkmenugetAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                drinkmenuSetRecyclerView()
            }
        }).execute()
    }

    private fun drinkmenuSetRecyclerView() {
        val com_DrinkMenu_Adapter = Com_DrinkMenu_Adapter(this)
        com_DrinkMenu_Adapter.submitList(drinkmenuList)
        drinkorderrecyclerview.apply {
            layoutManager = LinearLayoutManager(this@Completed_Order_Page)
            adapter = com_DrinkMenu_Adapter
        }

    }

    private fun drinkmenuDelete(drinkmenuEntity: DrinkmenuEntity) {
        var drinkmenudeleteTask = (object : AsyncTask<Unit, Unit, Unit>() {
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
        var sidemenugetTask = (object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                sidemenuList = sidedb.sidemenuDAO().sidemenugetALl()
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

        sideorderrecyclerview.apply {
            layoutManager = LinearLayoutManager(this@Completed_Order_Page)
            adapter = com_SideMenu_Adapter
        }
    }

    private fun sidemenuDelete(sidemenuEntity: SidemenuEntity) {
        var sidemenudeleteTask = (object : AsyncTask<Unit, Unit, Unit>() {
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
        val realmenugetallTask = (object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                realmenuList = realdb.realmenuDAO().realmenugetAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                realmenuSetRecyclerView()
            }

        }).execute()
    }

    private fun realmenuSetRecyclerView() {
        val com_RealMenu_Adapter = Com_RealMenu_Adapter(this)
        com_RealMenu_Adapter.submitList(realmenuList)

        realmenurecyclerview.apply {
            layoutManager = LinearLayoutManager(this@Completed_Order_Page)
            adapter = com_RealMenu_Adapter
        }

    }

    private fun realmenuDelete(realmenuEntity: RealmenuEntity) {
        val realmenuDeleteTask = (object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                realdb.realmenuDAO().realmenuDelete(realmenuEntity)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                realmenugetAll()
            }


        }).execute()
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
        val realmenugetallTask = (object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                realmenuList = realdb.realmenuDAO().realmenugetAll()

            }


            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)

                for (i in realmenuList.indices) {
                    count_sum += realmenuList.get(i).realmenuprice!!.toInt()
                }

                for (i in sidemenuList.indices)
                    count_sum += sidemenuList.get(i).sidemenuprice!!.toInt()

                for (i in drinkmenuList.indices)
                    count_sum += drinkmenuList.get(i).drinkmenuprice!!.toInt()

                Log.d("합계", "$count_sum")
                com_resultprice_txt.text = count_sum.toString() + " 원"
            }

        }).execute()
    }


    private fun menutotalOrder() {

        val smallOrderMenuItem = ArrayList<Small_Order_Menu_Item>()

        val realmenugetallTask = (object : AsyncTask<Unit, Unit, Unit>() {
            override fun doInBackground(vararg params: Unit?) {
                realmenuList = realdb.realmenuDAO().realmenugetAll()
            }


            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)

                for (i in realmenuList.indices) {

                    var realmenuname_id: Int
                    var realmenuscore_count: Int

                    realmenuname_id = realmenuList.get(i).realmenuid!!.toInt()
                    realmenuscore_count = realmenuList.get(i).realmenufoodscore!!.toInt()
                    val realmenul = Small_Order_Menu_Item(realmenuname_id, realmenuscore_count)
                    smallOrderMenuItem.add(realmenul)
                }

                for (i in sidemenuList.indices) {
                    var sidemenuname_id: Int
                    var sidemenuscore_count: Int

                    sidemenuname_id = sidemenuList.get(i).sidemenuid!!.toInt()
                    sidemenuscore_count = sidemenuList.get(i).sidemenufoodscore!!.toInt()
                    val sidemenul = Small_Order_Menu_Item(sidemenuname_id, sidemenuscore_count)

                    smallOrderMenuItem.add(sidemenul)

                }

                for (i in drinkmenuList.indices) {
                    var drinkmenuname_id: Int
                    var drinkmenuscore_count: Int

                    drinkmenuname_id = drinkmenuList.get(i).drinkmenuid!!.toInt()
                    drinkmenuscore_count = drinkmenuList.get(i).drinkmenufoodscore!!.toInt()
                    val drinkmenul = Small_Order_Menu_Item(drinkmenuname_id, drinkmenuscore_count)

                    smallOrderMenuItem.add(drinkmenul)


                }

                Log.d(TAG, "onPostExecute:$smallOrderMenuItem")



                Retrofit_Manager.retrofit_manger.OrderMenuPost(
                    Order_Menu_Item(
                        smallOrderMenuItem,
                        requestEditText.text.toString()
                    )
                )


            }

        }).execute()
    }


}