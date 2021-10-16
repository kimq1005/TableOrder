package com.example.ordermain_1.rerofit

import android.content.Context
import android.content.SharedPreferences
import com.example.ordermain_1.R

class WebSaveToken(context: Context) {
    private var webprefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.webtoken),
        Context.MODE_PRIVATE)


    companion object{
        const val WEB_USER_TOKEN = "accessToken"
    }

    fun websaveAccessToken(token:String){
        val editor = webprefs.edit()
        editor.putString(WEB_USER_TOKEN,token)
        editor.apply()
    }

    fun webreturnAccessToken():String?{
        return webprefs.getString(WEB_USER_TOKEN,null)
    }
}