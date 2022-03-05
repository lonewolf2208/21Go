package com.example.a21go.Repository

import androidx.lifecycle.MutableLiveData
import com.example.a21go.Network.Response
import com.example.a21go.Network.ServiceBuilder
import com.example.a21go.model.HomePageModel
import com.example.a21go.model.WallpapersModelItem
import retrofit2.Call
import retrofit2.Callback

class getWallpapersRepo {
    private val getWallpapersApiLiveData = MutableLiveData<Response<List<WallpapersModelItem>>>()
    fun getWallapapersApi(
        day:Int,
        ): MutableLiveData<Response<List<WallpapersModelItem>>> {
        val result = ServiceBuilder.buildService().getWallpapers(day)
       result.enqueue(object : Callback<List<WallpapersModelItem>?> {
           override fun onResponse(
               call: Call<List<WallpapersModelItem>?>,
               response: retrofit2.Response<List<WallpapersModelItem>?>
           ) {
               when{
                   response.isSuccessful->{getWallpapersApiLiveData.postValue(Response.Success(response.body()))}
                   else->{getWallpapersApiLiveData.postValue(Response.Error(response.message().toString()))}
               }
           }

           override fun onFailure(call: Call<List<WallpapersModelItem>?>, t: Throwable) {
               getWallpapersApiLiveData.postValue(Response.Error(t.localizedMessage.toString()))
           }
       })
        return getWallpapersApiLiveData
    }

}