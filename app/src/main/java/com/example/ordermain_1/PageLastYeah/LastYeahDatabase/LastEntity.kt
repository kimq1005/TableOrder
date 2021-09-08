package com.example.ordermain_1.PageLastYeah.LastYeahDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity (tableName="last")
data class LastEntity (

    @PrimaryKey(autoGenerate = true)
    var id : Long ?=null,

    var ordered_realmenu_1 : String ? =null,
    var ordered_realmenu_2 : String ?=null,
    var ordered_realmenu_3: String? = null,

    var ordered_sidemenu_1 : String ? =null,
    var ordered_sidemenu_2 : String ?=null,
    var ordered_sidemenu_3: String? = null,

    var ordered_dirnkmenu_1 : String ? =null,
    var ordered_drinkmenu_2 : String ?=null,
    var ordered_drinkmenu_3: String? = null,



        )