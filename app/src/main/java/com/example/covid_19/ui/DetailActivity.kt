package com.example.covid_19.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.API.CaseAPI
import com.example.covid_19.R
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.internal.schedulers.IoScheduler
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DetailActivity : AppCompatActivity() {
    var showCountryName: TextView? = null
    var showNewConfirmed: TextView? = null
    var showTotalConfirmed: TextView? = null
    var showNewDeaths: TextView? = null
    var showTotalDeaths: TextView? = null
    var showNewRecovered: TextView? = null
    var showTotalRecovered: TextView? = null
    var showDate: TextView? = null
    var showTime: TextView? = null
    var btnBack: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        showCountryName = findViewById(R.id.txtCountryName)
        showNewConfirmed = findViewById(R.id.txtNewConfirmed)
        showTotalConfirmed = findViewById(R.id.txtTotalConfirmed)
        showNewDeaths = findViewById(R.id.txtNewDeaths)
        showTotalDeaths = findViewById(R.id.txtTotalDeaths)
        showNewRecovered = findViewById(R.id.txtNewRecovered)
        showTotalRecovered = findViewById(R.id.txtTotalRecovered)
        showDate = findViewById(R.id.date)
        showTime = findViewById(R.id.time)
        btnBack = findViewById(R.id.back)

        val intent = intent
        showCountryName!!.text = "" + (intent.getStringExtra("name"))
        showNewConfirmed!!.text = "" + (intent.getStringExtra("newConfirmed"))
        showTotalConfirmed!!.text = "" + (intent.getStringExtra("totalConfirmed"))
        showNewDeaths!!.text = "" + (intent.getStringExtra("newDeaths"))
        showTotalDeaths!!.text = "" + (intent.getStringExtra("totalDeaths"))
        showNewRecovered!!.text = "" + (intent.getStringExtra("newRecovered"))
        showTotalRecovered!!.text = "" + (intent.getStringExtra("totalRecovered"))
        showDate!!.text = "" + (intent.getStringExtra("date")?.substring(0,10))
        showTime!!.text = "" + (intent.getStringExtra("date")?.substring(11,23))

        btnBack!!.setOnClickListener {
            val back = Intent(this, MainActivity::class.java)
            this.startActivity(back)
        }

        val url = "https://api.covid19api.com/"
        val retrofit = Retrofit.Builder().addConverterFactory(
            GsonConverterFactory.create(
                GsonBuilder().create()
            )
        ).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).baseUrl(url).build()

        val postCaseAPI = retrofit.create(CaseAPI::class.java)
        val responseCase = postCaseAPI.getCaseData()
        responseCase.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe{
        }
    }
}