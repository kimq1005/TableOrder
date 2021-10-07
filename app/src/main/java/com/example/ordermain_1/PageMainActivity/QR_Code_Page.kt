package com.example.ordermain_1.PageMainActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.ordermain_1.R
import com.google.zxing.integration.android.IntentIntegrator

class QR_Code_Page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_q_r__code__page)

        val integrator = IntentIntegrator(this)
        integrator.setPrompt("QR 코드 스캔해요")  //프롬프트
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)   //원하는 규격
        integrator.setCameraId(0)   //0은 후면, 1은 전면카메라
        integrator.setBeepEnabled(false)    //소리낼지말지
        integrator.setBarcodeImageEnabled(true) //이미지
        integrator.initiateScan()


    }

    
}