package com.example.ordermain_1.PageGoOrderPage.DrinkmenuDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(DrinkmenuEntity::class),version=2)
abstract class DrinkmenuDataBase : RoomDatabase(){
    abstract fun drinkmenuDAO() : DrinkmenuDAO

    companion object{
        private var INSTANCE : DrinkmenuDataBase? =null

        fun getinstance(context: Context) : DrinkmenuDataBase?{
            if(INSTANCE==null){
                synchronized(DrinkmenuDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    DrinkmenuDataBase::class.java, "drinkmenu.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }

            return INSTANCE
        }
    }
}