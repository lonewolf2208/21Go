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
//               else if (response.code() == 401) {
//
//                  loginLiveData.postValue(Response.Error("User not registered"))
//               }
//                else if(response.code()==400)
//               {
//                  loginLiveData.postValue(Response.Error("Wrong Password"))
//               }
               else {
                  loginLiveData.postValue(Response.Error(response.code().toString()))
                 // Log.d("RESPONSE BODY", response.code().toString())
               }
            }

            override fun onFailure(call: Call<Auth?>, t: Throwable) {
               loginLiveData.postValue(Response.Error("Something went wrong ${t.message}"))
            }
         })

   }
}