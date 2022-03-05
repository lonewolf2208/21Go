package com.example.a21go.Repository

import androidx.lifecycle.MutableLiveData
import com.example.a21go.Network.Response
import com.example.a21go.Network.ServiceBuilder
import com.example.a21go.model.CommunityForumDataClassItem
import com.example.a21go.model.WallpapersModelItem
import retrofit2.Call
import retrofit2.Callback

class CommunityForumRepo {
    private val getCommunityForumApiLiveData = MutableLiveData<Response<List<CommunityForumDataClassItem>>>()
    fun getCommunitytForumApi(

    ): MutableLiveData<Response<List<CommunityForumDataClassItem>>> {
        val result = ServiceBuilder.buildService().getForum()
       result.enqueue(object : Callback<List<CommunityForumDataClassItem>?> {
           override fun onResponse(
               call: Call<List<CommunityForumDataClassItem>?>,
               response: retrofit2.Response<List<CommunityForumDataClassItem>?>
           ) {
               when
               {
                   response.isSuccessful->{getCommunityForumApiLiveData.postValue(Response.Success(response.body()))}
                   else->{getCommunityForumApiLiveData.postValue(Response.Error(response.message()))}
               }
           }

           override fun onFailure(call: Call<List<CommunityForumDataClassItem>?>, t: Throwable) {
               getCommunityForumApiLiveData.postValue(Response.Error(t.localizedMessage))
           }
       })
        return getCommunityForumApiLiveData
    }
}