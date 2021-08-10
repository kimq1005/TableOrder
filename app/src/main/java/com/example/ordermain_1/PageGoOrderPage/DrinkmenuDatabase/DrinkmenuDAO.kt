package com.example.ordermain_1.PageGoOrderPage.DrinkmenuDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface DrinkmenuDAO {

    @Insert(onConflict = REPLACE)
    fun drinkmenuinsert(drinkmenuEntity: DrinkmenuEntity)

    @Query("SELECT * FROM drinkmenu")
    fun drinkmenugetAll() : List<DrinkmenuEntity>

    @Delete
    fun drinkmenudelete(drinkmenuEntity: DrinkmenuEntity)
}