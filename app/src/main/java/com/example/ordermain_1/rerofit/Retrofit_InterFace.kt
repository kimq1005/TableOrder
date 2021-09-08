package com.example.ordermain_1.rerofit

import com.example.ordermain_1.rerofit.util.API
import retrofit2.Call
import retrofit2.http.Query
import com.google.gson.JsonElement
import retrofit2.http.GET

interface Retrofit_InterFace {

    @GET(API.Menu_Name)
    fun MenuNameCall(@Query("query") MenuName:String) : Call<JsonElement>


    @GET(API.Menu_Price)
    fun MenuPriceCall(@Query("query") MenuPrice:String) : Call<JsonElement>
}