package com.example.a21go.Ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.a21go.Activity.HomePageActivity
import com.example.a21go.Network.Response
import com.example.a21go.R
import com.example.a21go.Repository.HomePageRepo
import com.example.a21go.databinding.FragmentSplashScreenBinding
import com.example.a21go.model.Auth
import com.example.a21go.model.HomePageModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch


class Splash_Screen : Fragment() {
    lateinit var binding:FragmentSplashScreenBinding

    companion object{
        lateinit var USERID:String
        lateinit var id:String
        var data = MutableLiveData<Response<Auth>>()
        var loggedIn :Boolean?= false
        var dataStore: DataStore<Preferences>? = null
        suspend fun save(key:String,value:Boolean)
        {
            val dataStoreKey= preferencesKey<Boolean>(key)
            dataStore?.edit { Settings->
                Settings[dataStoreKey]=value
            }
        }
        suspend fun readInfo(key:String):String?
        {
            val dataStoreKey= preferencesKey<String>(key)
            val preferences = dataStore?.data?.first()
            return preferences?.get(dataStoreKey)
        }
        suspend fun saveInfo(key:String,value:String)
        {
            val dataStoreKey= preferencesKey<String>(key)
            dataStore?.edit { Settings->
                Settings[dataStoreKey]=value
            }

        }
        suspend fun read(key:String):Boolean?
        {
            val dataStoreKey= preferencesKey<Boolean>(key)
            val preferences = dataStore?.data?.first()
            return preferences?.get(dataStoreKey)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataStore = context?.createDataStore(name = "Settings")!!
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_splash__screen, container, false)
        lifecycleScope.launch {
            Splash_Screen.id= readInfo("USERID").toString()
             loggedIn = read("loggedIn")
            if(loggedIn==true)
            {
                 data=HomePageRepo().HomePageApi(Splash_Screen.id!!.toInt())
            }
        }

        Handler().postDelayed(
            {
                lifecycleScope.launch {
                    if(loggedIn==true)
                    {
                        var intent=Intent(activity,HomePageActivity::class.java)
                        startActivity(intent)
                    }
                    else {
                        findNavController().navigate(R.id.loginFragment)
                    }
                }
            },3000
        )
        return binding.root
    }


}