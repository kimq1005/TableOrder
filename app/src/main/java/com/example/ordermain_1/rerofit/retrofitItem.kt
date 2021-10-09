package com.example.ordermain_1.rerofit

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class retrofitItem (
    var menuimage : String?,
    var menuname : String?,
    var menuprice : String?
        ):Serializable




data class PostResult(
    var status :Int?,
    var message : String?,
    var accessToken : String?,
    var expiredAt : String?
)



data class TableData (
    @SerializedName("username")
    var username :String?,

    @SerializedName("storeId")
    var storeId : Int?,

    @SerializedName("tableId")
    var tableId : Int?
)

data class Friend(
    var status :Int?,
    var message : String?,
    var accessToken : String?,
    var expiredAt : String?
)

data class MainMenulist(
    var menuimage : String?,
    var menuname : String?,
    var menuprice : String?
):Serializable


data class SideMenulist(
    var sidemenuimage : String?,
    var sidemenuname : String?,
    var sidemenuprice : String?
):Serializable

data class DrinkMenulist(
    var drinkmenuimage : String?,
    var drinkmenuname : String?,
    var drinkmenuprice : String?
):Serializable
