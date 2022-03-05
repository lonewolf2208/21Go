package com.example.a21go.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.a21go.Network.Response
import com.example.a21go.Network.ServiceBuilder
import com.example.a21go.model.HomePageModel
import com.example.a21go.model.StatsModel
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback

class StatsRepo() {
    private val StatsLiveData = MutableLiveData<Response<List<StatsModel>>>()
    fun Stats(id:Int): MutableLiveData<Response<List<StatsModel>>> {
        val result = ServiceBuilder.buildService().stats(id)
       result.enqueue(object : Callback<List<StatsModel>?> {
           override fun onResponse(
               call: Call<List<StatsModel>?>,
               response: retrofit2.Response<List<StatsModel>?>
           ) {
               when
               {
                   response.isSuccessful->{
                       StatsLiveData.postValue(Response.Success(response.body()))
                       Log.i("Helloprofilepost", "onActivityResult:"+response.body())
                   }

                   else->
                   {
                       StatsLiveData.postValue(Response.Error("Error"+response.code()))
                   }
               }
           }

           override fun onFailure(call: Call<List<StatsModel>?>, t: Throwable) {
           StatsLiveData.postValue(Response.Error("Error"))
           }
       })
        return StatsLiveData
    }
}