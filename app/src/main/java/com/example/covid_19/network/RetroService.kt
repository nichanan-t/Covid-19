package com.example.covid_19.network

import com.example.covid_19.models.RecyclerList
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {
    @GET("/countries")
    suspend fun getDataFromApi(@Query("q") query: String) : RecyclerList
}