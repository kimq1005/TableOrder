package com.example.ordermain_1.PageMenuPageUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ordermain_1.*
import com.example.ordermain_1.PageComOrderPage.Completed_Order_Page
import com.example.ordermain_1.rerofit.DrinkMenulist
import com.example.ordermain_1.rerofit.MainMenulist
import com.example.ordermain_1.rerofit.SideMenulist
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_menu_page_ui.*

class MenuPageUI : AppCompatActivity() {

    //Item
    private lateinit var menulist: ArrayList<MainMenulist>
    private lateinit var sidemenulist: ArrayList<SideMenulist>
    private lateinit var drinkmenulist: ArrayList<DrinkMenulist>


    private lateinit var realmenuAdapter: RealMenu_Adapter
    private lateinit var sidemenuAdapter: Sidemenu_Adapter
    private lateinit var drinkmeunAdapter: DrinkMeun_Adapter


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_page_ui)



        QR_icon_yeah.setOnClickListener {
            val integrator = IntentIntegrator(this)
            integrator.setPrompt("QR코드 스캔을 해주세요.")  //프롬프트
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)   //원하는 규격
            integrator.setCameraId(0)   //0은 후면, 1은 전면카메라
            integrator.setBeepEnabled(false)    //소리낼지말지
            integrator.setBarcodeImageEnabled(true) //이미지
            integrator.initiateScan()
        }

        orderlist_icon.setOnClickListener {
            val intent = Intent(this, Completed_Order_Page::class.java)
            startActivity(intent)
        }

        back_icon.setOnClickListener {
            onBackPressed()
        }

        scrollevent()
        initMenuPageUIAdapter()

        basketlist.setOnClickListener {
            val intent = Intent(this, Completed_Order_Page::class.java)
            startActivity(intent)
        }

    }

    private fun scrollevent() {

        mainbtn.setOnClickListener {
            scrollView2.scrollTo(0, realmenurecyclerView.top)
        }

        sidebtn.setOnClickListener {
            scrollView2.scrollTo(0, sidemenurecyclerView.top)
        }

        drinkbtn.setOnClickListener {
            scrollView2.scrollTo(0, drinkmenurecyclerView.top)
        }


    }


    private fun initMenuPageUIAdapter() {

        val bundle = intent.getBundleExtra("array_bundle")
        menulist = bundle?.getSerializable("menu_list") as ArrayList<MainMenulist>
        sidemenulist = bundle?.getSerializable("side_menu_list") as ArrayList<SideMenulist>
        drinkmenulist = bundle?.getSerializable("drink_menu_list") as ArrayList<DrinkMenulist>



        realmenurecyclerView.apply {

            realmenuAdapter = RealMenu_Adapter()
            realmenuAdapter.submitList(menulist)
            layoutManager = GridLayoutManager(this@MenuPageUI, 1, GridLayoutManager.VERTICAL, false)
            adapter = realmenuAdapter

        }

        sidemenurecyclerView.apply {


            sidemenuAdapter = Sidemenu_Adapter()
            sidemenuAdapter.submitList(sidemenulist)
            layoutManager = GridLayoutManager(this@MenuPageUI, 1, GridLayoutManager.VERTICAL, false)
            adapter = sidemenuAdapter
        }

        drinkmenurecyclerView.apply {
            drinkmeunAdapter = DrinkMeun_Adapter()
            drinkmeunAdapter.submitList(drinkmenulist)
            layoutManager = GridLayoutManager(this@MenuPageUI, 1, GridLayoutManager.VERTICAL, false)
            adapter = drinkmeunAdapter
        }


    }


}