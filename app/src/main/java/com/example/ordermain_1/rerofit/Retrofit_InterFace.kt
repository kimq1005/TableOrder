package com.example.ordermain_1.rerofit

import com.example.ordermain_1.rerofit.util.API
import retrofit2.Call
import retrofit2.http.Query
import com.google.gson.JsonElement
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface Retrofit_InterFace {

    @GET(API.Menu_Name)
    fun MenuNameCall(@Query("query") MenuName:String) : Call<JsonElement>


//    @POST(API.POST_URL)
//    fun CallPost(@Body tokenCallData: TokenCallData) : Call<PostResult>
}