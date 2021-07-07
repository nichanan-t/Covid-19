package com.example.covid_19.models

data class Covid (val Countries: ArrayList<RecyclerData>)

data class RecyclerData (
    val Country: String,
    val CountryCode: String,
    val NewConfirmed: Int,
    val TotalConfirmed: Int,
    val NewDeaths: Int,
    val TotalDeaths: Int,
    val NewRecovered: Int,
    val TotalRecovered: Int,
    val Date: String
        )