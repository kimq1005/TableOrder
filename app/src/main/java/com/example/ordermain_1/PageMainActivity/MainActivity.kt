package com.example.ordermain_1.PageMainActivity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.example.ordermain_1.PageLastYeah.LastYeah
import com.example.ordermain_1.PageMenuPageUI.MenuPageUI
import com.example.ordermain_1.R
import com.example.ordermain_1.rerofit.Retrofit_Manager
import com.example.ordermain_1.rerofit.TableData
import com.example.ordermain_1.rerofit.util.RESPONS_STATE
import com.google.zxing.integration.android.IntentIntegrator
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    val TAG : String = "로그"
//    private val plusList = ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Retrofit_Manager.retrofit_manger.PostRequest(TableData("kimsh",8,310))

        menucall()

//        Handler().postDelayed({
//            fadeanimation(this)
//        }, 1000)



    }

    private fun menucall() {
        Retrofit_Manager.retrofit_manger.HeaderTokenRequest(completion = {
            responsestate,menuarraylist, sidearraylist, drinkarraylist->

            val menulogd = menuarraylist
            Log.d(TAG, "menucall: $menulogd \n $sidearraylist \n $drinkarraylist")

            when(responsestate){
                RESPONS_STATE.OKAY->{

                    val intent = Intent(this,MenuPageUI::class.java)
                    val bundle = Bundle()

                    bundle.putSerializable("menu_list",menuarraylist)
                    bundle.putSerializable("side_menu_list",sidearraylist)
                    bundle.putSerializable("drink_menu_list",drinkarraylist)
                    intent.putExtra("array_bundle",bundle)

                    startActivity(intent)

                }

                RESPONS_STATE.FAIL->{
                    Log.d(TAG, "menucall: 으악실패다")
                }
            }
        })
    }

    fun fadeanimation(activity: Activity) {


        QRscan()

        activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }



    private fun QRscan() {
        val integrator = IntentIntegrator(this)
        integrator.setPrompt("QR 코드 스캔해요")  //프롬프트
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)   //원하는 규격
        integrator.setCameraId(0)   //0은 후면, 1은 전면카메라
        integrator.setBeepEnabled(false)    //소리낼지말지
        integrator.setBarcodeImageEnabled(true) //이미지
        integrator.initiateScan()

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if(result != null) {
            if(result.contents != null){

//                retrofitCall()

            }else{
                Toast.makeText(this,"Cancelled", Toast.LENGTH_SHORT).show()
            }

        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }

    }



    private fun retrofitCall() {


        Retrofit_Manager.retrofit_manger.CallMenuName(searchString = "pizza", completion = {
            responsestate,menuarrayList ->


            when(responsestate){
                RESPONS_STATE.OKAY->{
                    Log.d(TAG, "메인엑티비티 레트로핏 성공 : $menuarrayList ")


                    val intent = Intent(this,MenuPageUI::class.java)
                    val bundle = Bundle()

                    bundle.putSerializable("menu_list",menuarrayList)
                    intent.putExtra("array_bundle",bundle)

                    startActivity(intent)
                }

                RESPONS_STATE.FAIL->{
                    Log.d(TAG, "메인엑티비티 레트로핏실패요")
                }
            }
        })

    }

}