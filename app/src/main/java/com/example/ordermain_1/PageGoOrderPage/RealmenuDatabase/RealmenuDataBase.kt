package com.example.ordermain_1.PageGoOrderPage.RealmenuDatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(RealmenuEntity::class),version = 2)
abstract class RealmenuDataBase:RoomDatabase() {
    abstract fun realmenuDAO() : RealmenuDAO

    companion object{
        private var INSTANCE : RealmenuDataBase? =null

        fun getinstance(context: Context) : RealmenuDataBase?{
            if(INSTANCE==null){
                synchronized(RealmenuDataBase::class){
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                    RealmenuDataBase::class.java,"realmenu.db")
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return INSTANCE
        }
    }
}