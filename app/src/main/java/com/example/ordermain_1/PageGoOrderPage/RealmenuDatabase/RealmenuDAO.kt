package com.example.ordermain_1.PageGoOrderPage.RealmenuDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query


@Dao
interface RealmenuDAO {

    @Insert(onConflict = REPLACE)
    fun realmenuInsert(realmenuEntity: RealmenuEntity)

    @Query("SELECT * FROM realmenu")
    fun realmenugetAll() : List<RealmenuEntity>

    @Delete
    fun realmenuDelete(realmenuEntity: RealmenuEntity)
}