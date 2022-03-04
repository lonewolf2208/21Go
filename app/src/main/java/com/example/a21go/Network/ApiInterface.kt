package com.example.a21go.Network

import com.example.a21go.model.Auth
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface ApiInterface {
    @POST("/user-create/")
    fun login(@Body data: Auth): Call<Auth>

}