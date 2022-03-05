package com.example.a21go.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a21go.Network.Response
import com.example.a21go.Repository.StatsRepo
import com.example.a21go.model.StatsModel
import kotlinx.coroutines.launch

class StatsViewModel : ViewModel()  {
    var User_id =  MutableLiveData<Int>()
    private var StatsResult: MutableLiveData<Response<List<StatsModel>>> = MutableLiveData()
    val statsResult: LiveData<Response<List<StatsModel>>>
        get() = StatsResult
    fun SubmitStats() = viewModelScope.launch {
        StatsResult= User_id.value?.let { StatsRepo().Stats(it) }!!
        Log.i("Userid", "SubmitStats: "+User_id.value)

    }
}