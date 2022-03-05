package com.example.a21go.Network

import com.example.a21go.model.*
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*


interface ApiInterface {
    @POST("/user-create/")
    fun login(@Body data: Auth): Call<Auth>
    @GET("/home/{user_id}/")
    fun getHomePageApi(@Path("user_id") id: Int ):Call<Auth>
    @GET("/relapse/{user_id}/")
    fun stats(@Path("user_id") id: Int):Call<List<StatsModel>>
    @FormUrlEncoded
    @POST("/relapse-record/")
    fun relapseRecordApi(@Field("user")id:Int,@Field("reason")reason:String,@Field("best")best:Int):Call<ResponseBody>

    @GET("/wallpapers/{day}/")
    fun getWallpapers(@Path("day")day:Int):Call<List<WallpapersModelItem>>

    @GET(" /post/")
    fun getForum():Call<List<CommunityForumDataClassItem>>
    @FormUrlEncoded
    @POST(" /post/")
    fun postForum(@Field("user")id:Int,@Field("message")message:String,@Field("title")title:String):Call<ResponseBody>

    @GET("/journal/{user_id}/")
    fun getJournals(@Path("user_id")id:Int):Call<List<JournalsDataClass>>

    @FormUrlEncoded
    @POST("/journal/{user_id}/")
    fun postJournals(@Path("user_id")user:Int,@Field("entry")message:String,@Field("user")id:Int):Call<ResponseBody>
}