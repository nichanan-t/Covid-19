package com.example.covid_19.API

import com.example.covid_19.models.Country
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface CaseAPI {
    @GET("summary")
    fun getCaseData(): Observable<Country>
}