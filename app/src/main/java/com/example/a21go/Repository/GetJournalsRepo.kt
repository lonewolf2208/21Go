package com.example.a21go.Repository

import androidx.lifecycle.MutableLiveData
import com.example.a21go.Network.Response
import com.example.a21go.Network.ServiceBuilder
import com.example.a21go.model.CommunityForumDataClassItem
import com.example.a21go.model.JournalsDataClass
import retrofit2.Call
import retrofit2.Callback

class GetJournalsRepo {
    private val getJournalsForumApiLiveData = MutableLiveData<Response<List<JournalsDataClass>>>()
    fun getJournalsForumApi(
        id:Int
    ): MutableLiveData<Response<List<JournalsDataClass>>> {
        val result = ServiceBuilder.buildService().getJournals(id)
        result.enqueue(object : Callback<List<JournalsDataClass>?> {
            override fun onResponse(
                call: Call<List<JournalsDataClass>?>,
                response: retrofit2.Response<List<JournalsDataClass>?>
            ) {
                when
                {
                    response.isSuccessful->{getJournalsForumApiLiveData.postValue(Response.Success(response.body()))}
                    else->{getJournalsForumApiLiveData.postValue(Response.Error(response.message()))}
                }
            }

            override fun onFailure(call: Call<List<JournalsDataClass>?>, t: Throwable) {
                getJournalsForumApiLiveData.postValue(Response.Error(t.localizedMessage))
            }
        })
        return getJournalsForumApiLiveData
    }
}