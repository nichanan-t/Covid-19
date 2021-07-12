package com.example.covid_19.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.API.CovidAPI
import com.example.covid_19.R
import com.example.covid_19.adapter.Adapter
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.internal.schedulers.IoScheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    var searchBar = findViewById<SearchView>(R.id.search_bar)

    override fun onCreate(savedInstanceState: Bundle?) {
        var recyclerView: RecyclerView? = null
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        recyclerView = findViewById(R.id.recyclerView)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        val url = "https://api.covid19api.com/"
//        val retrofit = Retrofit.Builder().addConverterFactory(
//            GsonConverterFactory.create(
//                GsonBuilder().create()
//            )
//        ).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).baseUrl(url).build()
//
//        val postCountryAPI = retrofit.create(CovidAPI::class.java)
//        val response = postCountryAPI.getAllData()
//        response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler()).subscribe() {
//            recyclerView.adapter = Adapter(it.Countries, this)
//        }
//
//    }

        fun search() {
            io.reactivex.rxjava3.core.Observable.create<String> { emitter ->
                searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        return false
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        if (!emitter.isDisposed) {
                            emitter.onNext(newText)
                        }
                        return false
                    }
                })
            }

                .debounce(500, TimeUnit.MILLISECONDS)
                .distinctUntilChanged()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        Log.d("TAG", "Search: $it")
                        //viewModel.searchCountry(it)
                        var recyclerView: RecyclerView? = null
                        recyclerView = findViewById(R.id.recyclerView)
                        recyclerView.layoutManager = LinearLayoutManager(this)
                        val url = "https://api.covid19api.com/"
                        val retrofit = Retrofit.Builder().addConverterFactory(
                            GsonConverterFactory.create(
                                GsonBuilder().create()
                            )
                        ).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).baseUrl(url)
                            .build()

                        val postCountryAPI = retrofit.create(CovidAPI::class.java)
                        val response = postCountryAPI.getAllData()
                        response.observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(IoScheduler()).subscribe() {
                            recyclerView.adapter = Adapter(it.Countries, this)
                        }
                    },

                    {
                        Log.e("TAG", "Error : $it")
                    },

                    {
                        Log.d("TAG", "Complete")
                    }
                )
        }
    }
}