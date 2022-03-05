package com.example.a21go.Network

import com.example.a21go.model.Auth
import com.example.a21go.model.HomePageModel
import com.example.a21go.model.StatsModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {
    @POST("/user-create/")
    fun login(@Body data: Auth): Call<Auth>
    @GET("/home/{user_id}/")
    fun getHomePageApi(@Path("user_id") id: Int ):Call<HomePageModel>
    @GET("/relapse/{user_id}/")
    fun stats(@Path("user_id") id: Int):Call<List<StatsModel>>
}