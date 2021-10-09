package com.example.ordermain_1.rerofit

import android.content.Context
import android.content.SharedPreferences
import com.example.ordermain_1.R

class SaveToken(context: Context) {
    private var prefs:SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name),Context.MODE_PRIVATE)


    companion object{
        const val USER_TOKEN = "accessToken"
    }

    fun saveAccessToken(token:String){
        val editor = prefs.edit()
        editor.putString(USER_TOKEN,token)
        editor.apply()
    }

    fun returnAccessToken():String?{
        return prefs.getString(USER_TOKEN,null)
    }
}