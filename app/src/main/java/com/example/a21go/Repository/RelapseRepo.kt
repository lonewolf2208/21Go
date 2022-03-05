package com.example.a21go.Repository

import androidx.lifecycle.MutableLiveData
import com.example.a21go.Network.Response
import com.example.a21go.Network.ServiceBuilder
import com.example.a21go.model.HomePageModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback

class RelapseRepo {
    private val RelapseRepoLiveData = MutableLiveData<Response<ResponseBody>>()
    fun RelapseRepoApi(
        id:Int,
        reason:String,
        best:Int
        ): MutableLiveData<Response<ResponseBody>> {
        val result = ServiceBuilder.buildService().relapseRecordApi(id,reason, best)
        result.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(
                call: Call<ResponseBody?>,
                response: retrofit2.Response<ResponseBody?>
            ) {
                when
                {
                    response.isSuccessful->{RelapseRepoLiveData.postValue(Response.Success(response.body()))}
                    else->
                    {
                        RelapseRepoLiveData.postValue(Response.Error(response.message().toString()))
                    }
                }

            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                RelapseRepoLiveData.postValue(Response.Error("Error"))
            }
        })

        return RelapseRepoLiveData
    }

}