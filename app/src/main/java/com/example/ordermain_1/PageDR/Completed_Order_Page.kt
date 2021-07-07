package com.example.ordermain_1.PageDR

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ordermain_1.Adapter.*
import com.example.ordermain_1.Item.fakeComOrder
import com.example.ordermain_1.MainActivityViewModel
import com.example.ordermain_1.R
import kotlinx.android.synthetic.main.activity_completed_order_page.*

class Completed_Order_Page : AppCompatActivity() {


    private lateinit var comorderAdapter: ComOrder_Adapter


    private lateinit var viewModel : MainActivityViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_completed_order_page)



        viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        viewModel.setcomoderItems(fakeComOrder)

        initComOrderPageAdatper()
        initComOrderPageViewModel()

    }


    private fun initComOrderPageAdatper() {
        comorderrecyclerview.apply {
            comorderAdapter= ComOrder_Adapter()
            layoutManager = LinearLayoutManager(this@Completed_Order_Page,LinearLayoutManager.VERTICAL,false)
            adapter = comorderAdapter
        }
    }

    private fun initComOrderPageViewModel() {
        viewModel.comoderList.observe(this,{comoderList->
            comorderAdapter.submitList(comoderList)
        })
    }
}