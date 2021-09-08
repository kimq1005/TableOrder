package com.example.ordermain_1.rerofit.util

object Log{
    val TAG:String ="로그"
}



object API {
    const val BASE_URL : String ="https://api.unsplash.com/"
    const val CLIENT_ID : String= "l4MOU0EpHYvh3KAAR6JWfDGfM_WbdhoXnBa7MrXpdR4"
    const val Menu_Name: String = "/search/photos"
    const val Menu_Price: String = ""
}

enum class RESPONS_STATE{
    OKAY,
    FAIL
}