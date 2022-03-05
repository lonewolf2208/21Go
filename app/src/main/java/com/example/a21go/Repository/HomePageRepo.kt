package com.example.a21go.Repository

import androidx.lifecycle.MutableLiveData
import com.example.a21go.Network.Response
import com.example.a21go.Network.ServiceBuilder
import com.example.a21go.model.Auth
import com.example.a21go.model.HomePageModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback

class HomePageRepo() {
    private val HomePageApiLiveData = MutableLiveData<Response<Auth>>()
    fun HomePageApi(
        id:Int,
        
    ): MutableLiveData<Response<Auth>> {
        val result = ServiceBuilder.buildService().getHomePageApi(id)
       result.enqueue(object : Callback<Auth?> {
           override fun onResponse(
               call: Call<Auth?>,
               response: retrofit2.Response<Auth?>
           ) {
               when
               {
                   response.isSuccessful->{HomePageApiLiveData.postValue(Response.Success(response.body()))}
                   else->
                   {
                       HomePageApiLiveData.postValue(Response.Error("Error"))
                   }
               }
           }

           override fun onFailure(call: Call<Auth?>, t: Throwable) {
               HomePageApiLiveData.postValue(Response.Error("Error"))
           }
       })
        return HomePageApiLiveData
    }
}