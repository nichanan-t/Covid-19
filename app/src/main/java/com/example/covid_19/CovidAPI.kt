package com.example.covid_19

import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface CovidAPI {
    @GET("countries")
        fun getInfo(): Call<List<Country>>
}