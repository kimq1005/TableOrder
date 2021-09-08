package com.example.ordermain_1.PageLastYeah.LastYeahDatabase

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE


@Dao
interface LastDAO {

    @Insert(onConflict=REPLACE)
    fun lastinsert(lastEntity: LastEntity)

    @Query("SELECT * FROM last")
    fun lastgetAll() : List<LastEntity>

    @Delete
    fun lastmenudelete(lastEntity: LastEntity)
}