package com.example.ordermain_1.rerofit

import android.graphics.PostProcessor
import android.util.Log
import com.example.ordermain_1.App
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

    private lateinit var saveToken: SaveToken
    private lateinit var myaccesstoken:String

    private lateinit var websaveToken : WebSaveToken
    private lateinit var webmyaccesstoken:String

    private val retrofit_interface : Retrofit_InterFace?=
        Retrofit_client.getClient(API.BASE_URL)?.create(Retrofit_InterFace::class.java)

    val httpCall :Retrofit_InterFace? = Retrofit_client.getClient(API.BUMS_BASE_URL)?.create(Retrofit_InterFace::class.java)
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


    fun PostRequest(tableData: TableData){

        saveToken = SaveToken(App.instance)

        val call3 = httpCall?.MenuPost(tableData)

        call3?.enqueue(object:retrofit2.Callback<Friend>{
            override fun onResponse(call: Call<Friend>, response: Response<Friend>) {
                Log.d(TAG, "onResponse:${response.body()}")
                val postresponse = response.body()

                if(postresponse?.status==200){
                    myaccesstoken = postresponse.accessToken.toString()
                    saveToken.saveAccessToken(myaccesstoken)
                    Log.d(TAG, "포스트 토큰 확인 :$myaccesstoken")

                }
            }

            override fun onFailure(call: Call<Friend>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

        })
    }


    fun HeaderTokenRequest(completion:(RESPONS_STATE,ArrayList<MainMenulist>?,ArrayList<SideMenulist>?,ArrayList<DrinkMenulist>?)->Unit){
        val call = httpCall?.OrderHeaderPosts("Bearer ${saveToken.returnAccessToken()}")

        call?.enqueue(object:retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "안녕하세요감사해요: ${response.body()}")

                when(response.code()){
                    200->{
                        response.body()?.let{
                            val mainmenulist = ArrayList<MainMenulist>()
                            val sidemenulist = ArrayList<SideMenulist>()
                            val drinkmenulist = ArrayList<DrinkMenulist>()



                            val menubody = it.asJsonObject
                            val data = menubody.getAsJsonArray("data")  //data[]

                            data.forEach { data->
                                val datasObject= data.asJsonObject


                                val menuid = datasObject.get("id").asInt




                                when(menuid){
                                    200 , 238, 290, 381->{


                                        val items = datasObject.getAsJsonArray("items")
                                        items.forEach { items->
                                            val itemsObject = items.asJsonObject

                                            val mainmenuid = itemsObject.get("id").asInt
                                            val menuimage = itemsObject.get("image").asString
                                            val menuname = itemsObject.get("name").asString
                                            val menuprice = itemsObject.get("price").asInt


                                            val menuwow = MainMenulist(mainmenuid,
                                                menuimage,menuname,menuprice.toString())

//                                            test21.add(test21(menuid))
                                            mainmenulist.add(menuwow)
                                        }


                                    }

                                    201->{
                                        val items = datasObject.getAsJsonArray("items")
                                        items.forEach { items->
                                            val itemsObject = items.asJsonObject

                                            val sidemenuid = itemsObject.get("id").asInt
                                            val sidemenuimage = itemsObject.get("image").asString
                                            val sidemenuname = itemsObject.get("name").asString
                                            val sidemenuprice = itemsObject.get("price").asInt


                                            val sidemenuwow = SideMenulist(
                                                sidemenuid,
                                                sidemenuimage,
                                                sidemenuname,
                                                sidemenuprice.toString()
                                            )

                                            sidemenulist.add(sidemenuwow)
                                        }
                                    }

                                    202->{
                                        val items = datasObject.getAsJsonArray("items")

                                        items.forEach { items->
                                            val itemsObject = items.asJsonObject

                                            val drinkmenuid = itemsObject.get("id").asInt
                                            val drinkmenuimage = itemsObject.get("image").asString
                                            val drinkmenuname = itemsObject.get("name").asString
                                            val drinkmenuprice = itemsObject.get("price").asInt


                                            val drinkmenuwow = DrinkMenulist(
                                                drinkmenuid,
                                                drinkmenuimage,
                                                drinkmenuname,
                                                drinkmenuprice.toString()
                                            )

                                            drinkmenulist.add(drinkmenuwow)
                                        }
                                    }
                                }
//                                if(menuid == 200 and 220){
//                                    val items = datasObject.getAsJsonArray("items")
//                                    items.forEach { items->
//                                        val itemsObject = items.asJsonObject
//
//
//                                        val menuimage = itemsObject.get("image").asString
//                                        val menuname = itemsObject.get("name").asString
//                                        val menuprice = itemsObject.get("price").asInt
//
//
//                                        val menuwow = MainMenulist(
//                                            menuimage,menuname,menuprice.toString())
//
//                                        mainmenulist.add(menuwow)
//                                    }
//
//                                }

//                                if(menuid==201){
//                                    val items = datasObject.getAsJsonArray("items")
//                                    items.forEach { items->
//                                        val itemsObject = items.asJsonObject
//
//
//                                        val sidemenuimage = itemsObject.get("image").asString
//                                        val sidemenuname = itemsObject.get("name").asString
//                                        val sidemenuprice = itemsObject.get("price").asInt
//
//
//                                        val sidemenuwow = SideMenulist(
//                                            sidemenuimage,sidemenuname,sidemenuprice.toString())
//
//
//                                        sidemenulist.add(sidemenuwow)
//
//                                    }
//
//                                }

                            }
                            completion(RESPONS_STATE.OKAY,mainmenulist,sidemenulist,drinkmenulist)


                        }
                    }
                }
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                completion(RESPONS_STATE.FAIL,null,null,null)
            }

        })
    }


    fun OrderMenuPost(orderMenuItem: Order_Menu_Item){
        saveToken = SaveToken(App.instance)

        val call = httpCall?.MenuOrderPost("Bearer ${saveToken.returnAccessToken()}",orderMenuItem)

        call?.enqueue(object:retrofit2.Callback<MenuResult1>{
            override fun onResponse(call: Call<MenuResult1>, response: Response<MenuResult1>) {
                Log.d(TAG, "오더메뉴포스트 : ${response.body()}")
            }

            override fun onFailure(call: Call<MenuResult1>, t: Throwable) {
                Log.d(TAG, "onFailure: $t")
            }

        })
    }

    fun WebLoginPost(){
        val call = httpCall?.WebLogin(userpass("kch","1234"))


        call?.enqueue(object:retrofit2.Callback<webtoken>{
            override fun onResponse(call: Call<webtoken>, response: Response<webtoken>) {
                Log.d(TAG, "onResponse:${response.body()}")
                val webresponsebody = response.body()

                if(webresponsebody?.status==200){
                    webmyaccesstoken = webresponsebody.accessToken.toString()
                    Log.d(TAG, "onResponse:${webmyaccesstoken}")

                    val call2 = httpCall?.OrderCancle("Bearer ${webmyaccesstoken}")

                    call2?.enqueue(object :retrofit2.Callback<JsonElement>{
                        override fun onResponse(
                            call: Call<JsonElement>,
                            response: Response<JsonElement>,
                        ) {
                            Log.d(TAG, "주문이 취소 됐슴다 ${response.body()}")
                        }

                        override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                            Log.d(TAG, "onFailure:$t")

                        }

                    })


                }
            }

            override fun onFailure(call: Call<webtoken>, t: Throwable) {
                Log.d(TAG, "onFailure:$t")
            }

        })
    }

    fun OrderCanclePost(){
        val call = httpCall?.OrderCancle("Bearer ${webmyaccesstoken}")

        call?.enqueue(object :retrofit2.Callback<JsonElement>{
            override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                Log.d(TAG, "주문이 취소 됐슴다 ${response.raw()}")
            }

            override fun onFailure(call: Call<JsonElement>, t: Throwable) {
                Log.d(TAG, "$t")
            }

        })
    }



}