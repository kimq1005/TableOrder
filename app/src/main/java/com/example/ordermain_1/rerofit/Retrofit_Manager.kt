package com.example.ordermain_1.rerofit

import android.util.Log
import com.example.ordermain_1.rerofit.util.API
import com.example.ordermain_1.rerofit.util.Log.TAG
import com.example.ordermain_1.rerofit.util.RESPONS_STATE
import com.google.gson.JsonElement
import retrofit2.Call
import retrofit2.Response
import java.text.SimpleDateFormat

class Retrofit_Manager {

    companion object{
        val retrofit_manger = Retrofit_Manager()
    }

    private val retrofit_interface : Retrofit_InterFace?=
        Retrofit_client.getClient(API.BASE_URL)?.create(Retrofit_InterFace::class.java)

    fun CallMenuName(searchString:String?, completion:(RESPONS_STATE,ArrayList<retrofitItem>?)->Unit){

        val term = searchString.let{
            it
        }?:""

        val call = retrofit_interface?.MenuNameCall(term)?:return

        call.enqueue(object:retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "레트로핏매니저: CallMenuName 성공 : ${response.body().toString()} ")
                when(response.code()){
                    200->{
                        response.body()?.let{
                            val menuArray = ArrayList<retrofitItem>()

                            val responsebody = it.asJsonObject
                            val result = responsebody.getAsJsonArray("results")
                            val total = responsebody.get("total").asInt

                            Log.d(TAG, "레트로핏 매니저 토탈 값: $total")


                            result.forEach{ resultItem ->

                                val resultItemObject = resultItem.asJsonObject
                                val menuImageLink = resultItemObject.get("urls").asJsonObject.get("thumb").asString



                                val createdAt = resultItemObject.get("created_at").asString
                                val parser = SimpleDateFormat("yyyy-mm-dd'T'HH:mm:ss")
                                val formatter = SimpleDateFormat("yyyy")

                                val outputDateString = formatter.format(parser.parse(createdAt))

                                val menuinfo = retrofitItem(menuImageLink,outputDateString,outputDateString)
                                menuArray.add(menuinfo)
                            }

                            completion(RESPONS_STATE.OKAY,menuArray)
                        }
                    }
                }

            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "레트로핏매니저 : CallMenuName 실패 $t")
                completion(RESPONS_STATE.FAIL,null)
            }

        })
    }
}