package com.example.ordermain_1.PageGoOrderPage.RealmenuDatabase

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "realmenu")
data class RealmenuEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long?,
    var realmenuid:String? =null,
    var realmenuname: String? = null,
    var realmenuprice: String? = null,
    var realmenufoodscore: String? =null
)

