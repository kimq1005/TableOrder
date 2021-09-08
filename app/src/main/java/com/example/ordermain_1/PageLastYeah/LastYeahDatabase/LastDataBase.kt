package com.example.ordermain_1.PageLastYeah.LastYeahDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(LastEntity::class),version=1)
abstract class LastDataBase: RoomDatabase() {

    abstract fun lastDAO(): LastDAO

    companion object{
        private var INSTANCE : LastDataBase? =null

        fun getinstance(context: Context) : LastDataBase?{
            if(INSTANCE==null){
                synchronized(LastDataBase::class.java){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    LastDataBase::class.java,"last.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE
        }
    }


}