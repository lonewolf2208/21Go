package com.example.a21go.Network

import com.example.a21go.model.Auth
import com.example.a21go.model.HomePageModel
import com.example.a21go.model.WallpapersModelItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {
    @POST("/user-create/")
    fun login(@Body data: Auth): Call<Auth>
    @GET("/home/{user_id}/")
    fun getHomePageApi(@Path("user_id") id: Int ):Call<Auth>
    @FormUrlEncoded
    @POST("/relapse-record/")
    fun relapseRecordApi(@Field("user")id:Int,@Field("reason")reason:String,@Field("best")best:Int):Call<ResponseBody>

    @GET("/wallpapers/{day}/")
    fun getWallpapers(@Path("day")day:Int):Call<List<WallpapersModelItem>>
}