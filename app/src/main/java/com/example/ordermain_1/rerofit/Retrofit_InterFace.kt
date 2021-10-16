package com.example.ordermain_1.rerofit

import com.example.ordermain_1.rerofit.util.API
import retrofit2.Call
import com.google.gson.JsonElement
import retrofit2.http.*

interface Retrofit_InterFace {

    @GET(API.Menu_Name)
    fun MenuNameCall(@Query("query") MenuName:String) : Call<JsonElement>


    @POST(API.POST_URL)
    fun MenuPost(@Body tabledata:TableData) : Call<Friend>

    @GET(API.Menu_Url)
    fun OrderHeaderPosts(@Header("Authorization")
                   accessToken:String) : Call<JsonElement>

    @POST(API.Order_Url)
    fun MenuOrderPost(
        @Header("Authorization")accessToken:String,
        @Body item : Order_Menu_Item
    ):Call<MenuResult1>

    @POST(API.Web_Login_URL)
    fun WebLogin(
        @Body userpass:userpass
    ):Call<webtoken>

    @POST(API.Order_Cancle_URL)
    fun OrderCancle(
        @Header("Authorization")accessToken:String
    ):Call<JsonElement>


}