package com.example.ordermain_1.PageMenuPageUI

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.widget.ViewPager2
import com.example.ordermain_1.*
import com.example.ordermain_1.PageComOrderPage.Completed_Order_Page
import com.example.ordermain_1.rerofit.retrofitItem
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_menu_page_ui.*

class MenuPageUI : AppCompatActivity() {

            //Item
            private lateinit var menulist : ArrayList<retrofitItem>


//            private lateinit var viewPagerAdapter : ViewPagerAdapter
//            private lateinit var menuInformationRecyclerViewAdapter : MenuInformationRecyclerViewAdapter
            private lateinit var realmenuAdapter: RealMenu_Adapter
            private lateinit var sidemenuAdapter: Sidemenu_Adapter
            private lateinit var drinkmeunAdapter: DrinkMeun_Adapter
            private lateinit var viewModel : MainActivityViewModel


            override fun onCreate(savedInstanceState: Bundle?) {

                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_menu_page_ui)


                viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
//                viewModel.setBannerItems(fakeBannerItemList)
                viewModel.setmenuinformationItems(fakeMenuinformation)
                viewModel.setrealmenuItems(fakeRealMenu)
                viewModel.setsidemenuItems(fakeSideMenu)
                viewModel.setdrinkmenuItems(fakeDrinkMenu)




                QR_icon_yeah.setOnClickListener {
                    val integrator = IntentIntegrator(this)
                    integrator.setPrompt("QR 코드 스캔해요")  //프롬프트
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)   //원하는 규격
                    integrator.setCameraId(0)   //0은 후면, 1은 전면카메라
                    integrator.setBeepEnabled(false)    //소리낼지말지
                    integrator.setBarcodeImageEnabled(true) //이미지
                    integrator.initiateScan()
                }

                orderlist_icon.setOnClickListener {
                    val intent = Intent(this,Completed_Order_Page::class.java)
                    startActivity(intent)
                }

                back_icon.setOnClickListener {
                    onBackPressed()
                }

                //if(장바구니 담기 버튼이 클릭되었다면){데이터 절로 보내 ㅇㅋ}

                initMenuPageUIAdapter()
                initMenuPageUIViewModel()

                ohshit23.setOnClickListener {
                    val intent= Intent(this, Completed_Order_Page::class.java)
                    startActivity(intent)
                }

            }



    private fun initMenuPageUIAdapter() {

        val bundle = intent.getBundleExtra("array_bundle")
        menulist = bundle?.getSerializable("menu_list") as ArrayList<retrofitItem>


//        viewPager2.apply {
//            viewPagerAdapter = ViewPagerAdapter(this@MenuPageUI)
//            adapter = viewPagerAdapter
//            registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback(){
//                override fun onPageSelected(position: Int) {
//                    super.onPageSelected(position)
//                    tv_page_number.text = "${position+1}"
//                }
//            })
//        }
//
//        shopinformation.apply {
//            menuInformationRecyclerViewAdapter = MenuInformationRecyclerViewAdapter()
//            layoutManager = LinearLayoutManager(this@MenuPageUI, LinearLayoutManager.VERTICAL, false)
//            adapter = menuInformationRecyclerViewAdapter
//        }

        realmenurecyclerView.apply{

            realmenuAdapter = RealMenu_Adapter()
            realmenuAdapter.submitList(menulist)
            layoutManager = GridLayoutManager(this@MenuPageUI,1,GridLayoutManager.VERTICAL,false)
            adapter =  realmenuAdapter

        }

        sidemenurecyclerView.apply{

//            sidemenuAdapter = Sidemenu_Adapter()
//            layoutManager = LinearLayoutManager(this@MenuPageUI, LinearLayoutManager.VERTICAL,false)
//            adapter= sidemenuAdapter

            sidemenuAdapter = Sidemenu_Adapter()
            layoutManager = GridLayoutManager(this@MenuPageUI,1, GridLayoutManager.VERTICAL,false)
            adapter = sidemenuAdapter
        }

       drinkmenurecyclerView.apply{
            drinkmeunAdapter = DrinkMeun_Adapter()
            layoutManager = GridLayoutManager(this@MenuPageUI,1, LinearLayoutManager.VERTICAL,false)
            adapter = drinkmeunAdapter
        }


    }


    private fun initMenuPageUIViewModel() {
//        viewModel.bannerItemList.observe(this, Observer { bannerItemList ->
//            viewPagerAdapter.submitList(bannerItemList)
//        })
//
//        viewModel.menuinformationitemList.observe(this, { menuinformationitemList ->
//            menuInformationRecyclerViewAdapter.submitList(menuinformationitemList)
//        })

//        viewModel.realmenuList.observe(this, { realmenuList ->
//            realmenuAdapter.submitList(menulist)
//        })

        viewModel.sidemenuList.observe(this,{sidemenuList->
            sidemenuAdapter.submitList(sidemenuList)
        })

        viewModel.drinkmenuList.observe(this,{drinkmenuList->
            drinkmeunAdapter.submitList(drinkmenuList)
        })
        


    }

}