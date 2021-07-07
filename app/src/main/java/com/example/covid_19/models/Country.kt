package com.example.covid_19.models

import com.google.gson.annotations.SerializedName

data class Country (
    @SerializedName("Country") val country: String,
    @SerializedName("Slug") val slug: String,
    @SerializedName("ISO2") val code: String
)