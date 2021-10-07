package com.example.ordermain_1.rerofit

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class retrofitItem (
    var menuimage : String?,
    var menuname : String?,
    var menuprice : String?
        ):Serializable


data class TokenCallData(
    @SerializedName("username")
    var username :String?,

    @SerializedName("storeId")
    var storeId : Int?,

    @SerializedName("tableId")
    var tableId : Int?
)


data class PostResult(
    var status :Int?,
    var message : String?,
    var accessToken : String?,
    var expiredAt : String?
)