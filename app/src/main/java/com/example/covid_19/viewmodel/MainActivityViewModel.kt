package com.example.covid_19.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.covid_19.models.RecyclerList
import com.example.covid_19.network.RetroInstance
import com.example.covid_19.network.RetroService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.create

class MainActivityViewModel : ViewModel() {
    lateinit var recyclerListLiveData : MutableLiveData<RecyclerList>

    init {
        recyclerListLiveData = MutableLiveData()
    }

    fun  getRecyclerListObserver() : MutableLiveData<RecyclerList>{
        return recyclerListLiveData
    }

    fun makeApiCall() {
        viewModelScope.launch(Dispatchers.IO) {
            val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
            val response = retroInstance.getDataFromApi("covid")
            recyclerListLiveData.postValue(response)
        }
    }
}



