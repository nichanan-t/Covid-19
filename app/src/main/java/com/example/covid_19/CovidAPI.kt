package com.example.covid_19

import com.example.covid_19.data.Country
import retrofit2.Call
import retrofit2.http.GET

interface CovidAPI {
    @GET("/countries")
        fun getInfo(): Call<List<Country>>
}