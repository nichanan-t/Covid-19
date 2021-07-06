package com.example.covid_19.models

data class RecyclerList (val Countries: ArrayList<RecyclerData>)
data class RecyclerData(
    val Country: String,
    val CountryCode:String,
    val NewConfirmed:String,
    val TotalConfirmed:String,
    val NewDeaths:String,
    val TotalDeaths:String,
    val NewRecovered:String,
    val TotalRecovered:String,
    val Date:String
    )
