package com.example.covid_19

import com.example.covid_19.data.Result
import retrofit2.Call
import retrofit2.http.GET

interface ResultAPI {
    @GET("/summary")
    fun getInfo():Call<List<Result>>
}