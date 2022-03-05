package com.example.a21go.Repository

import androidx.lifecycle.MutableLiveData
import com.example.a21go.Network.Response
import com.example.a21go.Network.ServiceBuilder
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback

class PostJournalsRepo {
    private val postJournalsApiLiveData = MutableLiveData<Response<ResponseBody>>()
    fun postJournalsApi(
        id:Int,message:String
    ): MutableLiveData<Response<ResponseBody>> {
        val result = ServiceBuilder.buildService().postJournals(id,message,id)
       result.enqueue(object : Callback<ResponseBody?> {
           override fun onResponse(
               call: Call<ResponseBody?>,
               response: retrofit2.Response<ResponseBody?>
           ) {
               when
               {
                   response.isSuccessful->{postJournalsApiLiveData.postValue(Response.Success())}
                   else->{postJournalsApiLiveData.postValue(Response.Error("Error"))}
               }
           }

           override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
               postJournalsApiLiveData.postValue(Response.Error("Error"))
           }
       })
        return postJournalsApiLiveData
    }
}