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
    var id:Int?,
    var menuimage : String?,
    var menuname : String?,
    var menuprice : String?
):Serializable


data class SideMenulist(
    var id:Int?,
    var sidemenuimage : String?,
    var sidemenuname : String?,
    var sidemenuprice : String?
):Serializable

data class DrinkMenulist(
    var id:Int?,
    var drinkmenuimage : String?,
    var drinkmenuname : String?,
    var drinkmenuprice : String?
):Serializable


data class Order_Menu_Item(
    @SerializedName("items")
    val menu_item: List<Small_Order_Menu_Item>?,
    @SerializedName("request")
    val request: String?
)

data class Small_Order_Menu_Item(
    val id : Int?,
    var count: Int?
)


data class MenuResult1(
    var status:String?,
    var message:String,
    var data : List<menudata>?
)


data class menudata(
    var id:Int?,
    var name :String?,
    var orderPrice:Int?,
    var count :Int?,
    var request :String?,
    var orderStatus:String?
)


data class test21(
    var id:Int?,
    var count:Int?,
)