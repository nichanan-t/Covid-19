package com.example.covid_19.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.api.CovidAPI
import com.example.covid_19.R
import com.example.covid_19.databinding.ActivityMainBinding
import com.example.covid_19.models.Data
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.internal.schedulers.IoScheduler
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var tb: Toolbar? = null
    private var countryList = arrayListOf<Data>()
    private var filteredList = arrayListOf<Data>()
    private lateinit var binding: ActivityMainBinding
    private val url = "https://api.covid19api.com/"
    private val retrofit = Retrofit.Builder().addConverterFactory(
        GsonConverterFactory.create(
            GsonBuilder().create()
        )
    ).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).baseUrl(url).build()
    private val postCountryAPI = retrofit.create(CovidAPI::class.java)
    private val response = postCountryAPI.getAllData()
    private val currentDate: String = SimpleDateFormat("EEE, d MMM yyyy z", Locale.getDefault()).format(Date())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tb = findViewById(R.id.toolbar)
        tb?.subtitle = currentDate
        setSupportActionBar(tb)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView?.layoutManager = LinearLayoutManager(this)

        val covidAdapter = com.example.covid_19.adapter.Adapter(countryList, this)
        recyclerView?.adapter = covidAdapter
        recyclerView?.setHasFixedSize(true)

        binding.searchCountry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {
                val charSearch = s.toString()

                if (charSearch.isEmpty()) {
                    filteredList = countryList
                    recyclerView?.adapter =
                    com.example.covid_19.adapter.Adapter(filteredList, this@MainActivity)
                } else {
                    val resultList = ArrayList<Data>()

                    for (row in countryList) {
                        if (row.country.lowercase(Locale.ROOT)
                                    .startsWith(charSearch.lowercase(Locale.ROOT))
                        )
                        {
                            resultList.add(row)
                            filteredList = resultList
                        }

                        recyclerView?.adapter =
                        com.example.covid_19.adapter.Adapter(resultList, this@MainActivity)
                        }
                        covidAdapter.notifyDataSetChanged()
                    }
                }
            })
        getAllData()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_items, menu)
        return true
    }

    private fun getAllData() {
        response.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(IoScheduler())
            .subscribe {
                countryList = it.Countries
                recyclerView?.adapter = com.example.covid_19.adapter.Adapter(countryList, this)
        }
    }
}