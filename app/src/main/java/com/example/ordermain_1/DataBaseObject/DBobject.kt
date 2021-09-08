package com.example.ordermain_1.DataBaseObject

import android.annotation.SuppressLint
import android.os.AsyncTask
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ordermain_1.PageComOrderPage.Com_RealMenu_Adapter
import com.example.ordermain_1.PageGoOrderPage.RealmenuDatabase.RealmenuDataBase
import com.example.ordermain_1.PageGoOrderPage.RealmenuDatabase.RealmenuEntity
import kotlinx.android.synthetic.main.activity_completed_order_page.*

@SuppressLint("StaticFieldLeak")
object DBobject {



    lateinit var realmenuList : List<RealmenuEntity>
    lateinit var realdb : RealmenuDataBase
    lateinit var mysum : MutableList<String>

    fun realmenugetAll() {
        val realmenugetallTask=(object: AsyncTask<Unit, Unit, Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                realmenuList=realdb.realmenuDAO().realmenugetAll()
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)

            }

        }).execute()
    }



    fun realmenuDelete(realmenuEntity: RealmenuEntity){
        val realmenuDeleteTask=(object: AsyncTask<Unit, Unit, Unit>(){
            override fun doInBackground(vararg params: Unit?) {
                realdb.realmenuDAO().realmenuDelete(realmenuEntity)
            }

            override fun onPostExecute(result: Unit?) {
                super.onPostExecute(result)
                realmenugetAll()
            }

        }).execute()
    }



}

