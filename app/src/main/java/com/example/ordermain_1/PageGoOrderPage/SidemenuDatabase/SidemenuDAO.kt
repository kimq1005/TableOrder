package com.example.ordermain_1.PageGoOrderPage.SidemenuDatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface SidemenuDAO {
    @Insert(onConflict = REPLACE)
    fun sidemenuinsert(sidemenuEntity: SidemenuEntity)

    @Query("SELECT * FROM sidemenu ")
    fun sidemenugetALl() : List<SidemenuEntity>

    @Delete
    fun sidemenuDelete(sidemenuEntity: SidemenuEntity)
}