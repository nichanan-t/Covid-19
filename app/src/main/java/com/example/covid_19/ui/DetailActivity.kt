package com.example.covid_19.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.API.CaseAPI
import com.example.covid_19.R
import com.example.covid_19.adapter.CaseAdapter
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.internal.schedulers.IoScheduler
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : AppCompatActivity() {
    var txtCountryName : TextView? = null
    var recyclerInfo: RecyclerView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        txtCountryName = findViewById(R.id.txtCountryName)
        var intent = intent
        txtCountryName!!.text = intent.getStringExtra("CountryCode")

        recyclerInfo = findViewById(R.id.recyclerInfo)
        recyclerInfo!!.layoutManager = LinearLayoutManager(this)
        var url = "https://api.covid19api.com/"
        val retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()
            )
        ).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).baseUrl(url).build()

        val postCaseAPI = retrofit.create(CaseAPI::class.java)
        val responseCase = postCaseAPI.getCaseData()
        responseCase.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe{
            recyclerInfo!!.adapter = CaseAdapter(it, this)
        }
    }
}