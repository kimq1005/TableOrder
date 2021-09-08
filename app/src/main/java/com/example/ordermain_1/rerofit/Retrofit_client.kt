package com.example.ordermain_1.rerofit

import android.util.Log
import com.example.ordermain_1.rerofit.util.API
import com.example.ordermain_1.rerofit.util.Log.TAG
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit_client {

   private var retrofitClient : Retrofit? =null

   fun getClient(baseurl:String) : Retrofit?{
      Log.d(TAG, "레트로핏클라이언트 getClient Called ")

      val okhttpClient = OkHttpClient.Builder()

      val loggingInterceptor = HttpLoggingInterceptor(object: HttpLoggingInterceptor.Logger{
         override fun log(message: String) {
//            Log.d(TAG, "레트로핏 클라이언트 로깅메세지: $message")
         }

      })

      loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

      okhttpClient.addInterceptor(loggingInterceptor)


      val baseInterceptor: Interceptor = (object :Interceptor{
         override fun intercept(chain: Interceptor.Chain): Response {

            val realRequest = chain.request()

            val goaddurl = realRequest
               .url
               .newBuilder()
               .addQueryParameter("client_id",API.CLIENT_ID)
               .build()

            val lastRequest = realRequest.newBuilder()
               .url(goaddurl)
               .method(realRequest.method, realRequest.body)
               .build()

            return chain.proceed(lastRequest)
         }

      })


      okhttpClient.addInterceptor(baseInterceptor)


      if(retrofitClient==null){
         retrofitClient=Retrofit.Builder()
            .baseUrl(API.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okhttpClient.build())
            .build()
      }

      return retrofitClient
   }

}