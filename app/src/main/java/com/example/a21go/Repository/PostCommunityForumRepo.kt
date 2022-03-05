package com.example.a21go.Repository

import androidx.lifecycle.MutableLiveData
import com.example.a21go.Network.Response
import com.example.a21go.Network.ServiceBuilder
import com.example.a21go.model.CommunityForumDataClassItem
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback

class PostCommunityForumRepo {
    private val postCommunityForumApiLiveData = MutableLiveData<Response<ResponseBody>>()
    fun postCommunitytForumApi(
        id:Int,message:String,title:String
    ): MutableLiveData<Response<ResponseBody>> {
        val result = ServiceBuilder.buildService().postForum(id, message, title)
        result.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(
                call: Call<ResponseBody?>,
                response: retrofit2.Response<ResponseBody?>
            ) {
                when
                {
                    response.isSuccessful->{postCommunityForumApiLiveData.postValue(Response.Success(response.body()))}
                    else->
                    {
                        postCommunityForumApiLiveData.postValue(Response.Error(response.message()))
                    }
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                postCommunityForumApiLiveData.postValue(Response.Error(t.localizedMessage))
            }
        })
        return postCommunityForumApiLiveData
    }
}