package com.example.a21go.Repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.a21go.Network.Response
import com.example.a21go.Network.ServiceBuilder
import com.example.a21go.model.Auth
import retrofit2.Call
import retrofit2.Callback

class LoginRepo {

   private val loginLiveData= MutableLiveData<Response<Auth>>()
   var userDetails = MutableLiveData<Auth>()
   val loginResponse: MutableLiveData<Response<Auth>>
   get()=loginLiveData

   fun loginApi(email:String,username:String) {

      val request = ServiceBuilder.buildService()
      val call = request.login(
         Auth(
            email = email,
            username = username
         )
      )
      loginLiveData.postValue(Response.Loading())
         call.enqueue(object : Callback<Auth?> {
            override fun onResponse(
                call: Call<Auth?>,
                response: retrofit2.Response<Auth?>
            ) {
               if (response.isSuccessful) {

                  loginLiveData.postValue(Response.Success(response.body()))
                  Log.d("RESPONSE BODY", response.body().toString())
                  userDetails.value=response.body()

               }
               else if (response.code() == 400) {
                  loginLiveData.postValue(Response.Error("User name already exits.Please Choose a Unique Username."))
               }
                else if(response.code()==406)
               {
                  loginLiveData.postValue(Response.Error("Email Already Registered with a different username"))
               }
               else {
                  loginLiveData.postValue(Response.Error("Something went wrong . Please try again!!"))

               }
            }

            override fun onFailure(call: Call<Auth?>, t: Throwable) {
               loginLiveData.postValue(Response.Error("Something went wrong ${t.message}"))
            }
         })

   }
}