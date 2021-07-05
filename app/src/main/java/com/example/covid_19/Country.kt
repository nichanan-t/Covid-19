package com.example.covid_19

import com.google.gson.annotations.SerializedName

class Country (
        @SerializedName("Country") val country:String,
        @SerializedName("Slug") val slug:String,
        @SerializedName("ISO2") val countryCode:String
)
