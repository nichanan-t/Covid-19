package com.example.covid_19.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    override fun onCreate(savedInstanceState: Bundle?) {
        var recyclerInfo: RecyclerView? = null
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        recyclerInfo = findViewById(R.id.recyclerInfo)
        recyclerInfo.layoutManager = LinearLayoutManager(this)
        val url = "https://api.covid19api.com/"
        val retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()
            )
        ).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).baseUrl(url).build()

        val postCaseAPI = retrofit.create(CaseAPI::class.java)
        val responseCase = postCaseAPI.getCaseData()
        responseCase.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe{
            recyclerInfo.adapter = CaseAdapter(it.Countries, this)
        }
    }
}