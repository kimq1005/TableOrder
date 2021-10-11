package com.example.ordermain_1.PageGoOrderPage.SidemenuDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(SidemenuEntity::class),version =2)
abstract class SidemenuDataBase:RoomDatabase() {
    abstract fun sidemenuDAO() : SidemenuDAO

    companion object{
        private var INSTANCE : SidemenuDataBase? =null

        fun getinstance(context: Context) : SidemenuDataBase?{
            if(INSTANCE == null){
                synchronized(SidemenuDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    SidemenuDataBase::class.java,"sidemenu.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE
        }
    }
}