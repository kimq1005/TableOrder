package com.example.ordermain_1.rerofit.util

object Log{
    val TAG:String ="로그"
}



object API {
    const val BASE_URL : String ="https://api.unsplash.com/"
    const val CLIENT_ID : String= "l4MOU0EpHYvh3KAAR6JWfDGfM_WbdhoXnBa7MrXpdR4"
    const val Menu_Name: String = "search/photos"



    const val Menu_Price: String = ""
    const val POST_URL : String = "app/customers"
    const val BUMS_BASE_URL : String = "http://13.124.45.246:8080/"
    const val Menu_Url:String="app/categories/items"
}

enum class RESPONS_STATE{
    OKAY,
    FAIL
}