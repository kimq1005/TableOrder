package com.example.ordermain_1.PageGoOrderPage.SidemenuDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "sidemenu")
data class SidemenuEntity (
    @PrimaryKey(autoGenerate = true)

    var id : Long?,
    var sidemenuid:String? =null,
    var sidemenuname : String? =null,
    var sidemenuprice : String? =null,
    var sidemenufoodscore: String? = null
    )