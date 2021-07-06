package com.example.covid_19.data.api

import android.telecom.Call
import com.example.covid_19.data.Country
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

val BASE_URL = "https://api.covid19api.com/"
    public interface ApiService {

        @GET("countries")
        fun getCountries(): retrofit2.Call<List<Country>>

        companion object{
            fun retrofitBuild(): ApiService{
                return Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
            }
        }
    }
