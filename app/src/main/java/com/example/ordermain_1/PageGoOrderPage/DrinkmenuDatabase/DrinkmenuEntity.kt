package com.example.ordermain_1.PageGoOrderPage.DrinkmenuDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drinkmenu")
data class DrinkmenuEntity(
    @PrimaryKey(autoGenerate = true)
    var id : Long?=null,

    var drinkmenuid:String? =null,
    var drinkmenuname : String? =null,
    var drinkmenuprice :  String? =null,
    var drinkmenufoodscore : String? =null
)