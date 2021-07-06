package com.example.covid_19.data

import com.google.gson.annotations.SerializedName

data class Result (
    val newConfirmed: Int,
    val totalConfirmed: Int,
    val newDeaths: Int,
    val totalDeaths: Int,
    val newRecovered: Int,
    val totalRecovered: Int
)