package com.example.ordermain_1.rerofit

import java.io.Serializable

data class retrofitItem (
    var menuimage : String?,
    var menuname : String?,
    var menuprice : String?
        ):Serializable


data class retrofitSideItem(
    var sidemenuimage : String?,
    var sidemenuname : String?,
    var sidemenuprice : String?
):Serializable


data class retrofitDrinkItem(
    var drinkmenuimage : String?,
    var drinkmenuname : String?,
    var drinkmenuprice : String?
):Serializable