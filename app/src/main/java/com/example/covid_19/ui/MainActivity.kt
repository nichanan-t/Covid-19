package com.example.covid_19.ui

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils.isEmpty
import android.text.TextWatcher
import android.text.method.TextKeyListener.clear
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Adapter
import android.widget.EditText
import android.widget.Filter
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Transformations.distinctUntilChanged
import androidx.lifecycle.Transformations.map
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.API.CovidAPI
import com.example.covid_19.R
import com.example.covid_19.databinding.ActivityMainBinding
import com.example.covid_19.models.Country
import com.example.covid_19.models.Data
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.internal.schedulers.IoScheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

//    var adapter: com.example.covid_19.adapter.Adapter
    var recyclerView: RecyclerView? = null

//    private var recyclerView: RecyclerView? = null
    private var countryList = arrayListOf<Data>()
    private var filteredList = arrayListOf<Data>()

    private lateinit var binding: ActivityMainBinding

    val url = "https://api.covid19api.com/"
    val retrofit = Retrofit.Builder().addConverterFactory(
        GsonConverterFactory.create(
            GsonBuilder().create()
        )
    ).addCallAdapterFactory(RxJava3CallAdapterFactory.create()).baseUrl(url).build()
    val postCountryAPI = retrofit.create(CovidAPI::class.java)
    val response = postCountryAPI.getAllData()

//    private var layoutManager: RecyclerView.LayoutManager? = null
//    private val data: List<Data> = ArrayList<Data>()
//    private val adapter: Adapter? = null
//    private val TAG = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
//        var searchBar = findViewById<EditText>(R.id.search_bar)
//        var recyclerView: RecyclerView? = null

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        searchBar = findViewById(R.id.search_country)
//        layoutManager = LinearLayoutManager(this@MainActivity)
//        recyclerView!!.setLayoutManager(layoutManager)
//        recyclerView!!.setItemAnimator(DefaultItemAnimator())
//        recyclerView!!.setNestedScrollingEnabled(false)

//        recyclerView = findViewById(R.id.recyclerView)
//        recyclerView!!.layoutManager = LinearLayoutManager(this)

        recyclerView = findViewById(R.id.recyclerView) as RecyclerView
        recyclerView!!.layoutManager = LinearLayoutManager(this)
//        recyclerView!!.layoutManager = LinearLayoutManager(recyclerView!!.context)

        val covidAdapter = com.example.covid_19.adapter.Adapter(countryList, this)
        recyclerView!!.adapter = covidAdapter

        recyclerView!!.setHasFixedSize(true)

        binding.searchCountry.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

            override fun afterTextChanged(s: Editable?) {

                val charSearch = s.toString()

                if (charSearch.isEmpty()) {
                    filteredList = countryList
                    recyclerView!!.adapter =
                    com.example.covid_19.adapter.Adapter(filteredList, this@MainActivity)
                } else {
                    val resultList = ArrayList<Data>()

                    for (row in countryList) {
                        if (row.country.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        )
                        {
                            resultList.add(row)
                            filteredList = resultList
                        }

                        recyclerView!!.adapter =
                        com.example.covid_19.adapter.Adapter(resultList, this@MainActivity)
                        }
                        covidAdapter.notifyDataSetChanged()
                    }
                }
            })



//                    filteredList = resultList
//                    filteredList = results?.values as ArrayList<Data>





//                adapter.getFilter()
//                val textSearch = binding.searchCountry.text.toString()
//
//                countryList.forEach {
//                    if (it.country.toLowerCase().startsWith(textSearch)) {
//                        filteredList.add(it)
//                        recyclerView!!.adapter =
//                            com.example.covid_19.adapter.Adapter(filteredList, this@MainActivity)
//                    } else {
//                        recyclerView!!.adapter =
//                            com.example.covid_19.adapter.Adapter(arrayListOf(it), this@MainActivity)
//                    }
//                }
//            }
//        })

        getAllData()
    }

    fun getAllData() {
        response.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(IoScheduler())
            .subscribe() {
                countryList = it.Countries
                recyclerView!!.adapter = com.example.covid_19.adapter.Adapter(countryList, this)
        }
    }
}



//        LoadJSON()
//    }
//
//    class LoadJSON {
//        val retrofit = Retrofit.Builder()
//            .baseUrl(MainActivity.Companion.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        val request: CovidAPI = retrofit.create(CovidAPI::class.java)
//        val call: Observable<Country> = request.getAllData()
//    }
//
//    companion object {
//        const val BASE_URL = "https://api.covid19api.com/"
//    }
//
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu_main, menu)
//        val searchBar = findViewById<SearchView>(R.id.search_bar)
//        searchBar = menu?.findItem(R.id.app)
//
//
//        return super.onCreateOptionsMenu(menu)
//    }


//    private fun loadJSON() {
//        val retrofit = Retrofit.Builder()
////            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
////            .addCallAdapterFactory(RxJava3CallAdapterFactory.create()).baseUrl(url)
////            .build()
//            .baseUrl(MainActivity.Companion.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//        val request: CovidAPI = retrofit.create(CovidAPI::class.java)
//        val call: Observable<Country> = request.getAllData()
//    }


//    fun search() {
//        val searchBar = findViewById<SearchView>(R.id.search_bar)
//        io.reactivex.rxjava3.core.Observable.create<String> { emitter ->
//            searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    return false
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    if (!emitter.isDisposed) {
//                        emitter.onNext(newText)
//                    }
//                    return false
//                }
//            })
//        }
//
//            .debounce(400, TimeUnit.MILLISECONDS)
//            .filter(text -> !text.isEmpty() && text.length() >= 3)
//            .map(text -> text.toLowerCase().trim())
////            .map(CharSequence::toString)
//            .distinctUntilChanged()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                Log.d("TAG", "Search: $it"
//                //viewModel.searchCountry(it)
//
//
//
//            }
//    },
//
//                {
//                    Log.e("TAG", "Error : $it")
//                },
//
//                {
//                    Log.d("TAG", "Complete")
//                }
//            )
//    }
//
//}



//        call.enqueue(object : Callback<JSONResponse?>() {
//            override fun onResponse(call: Call<JSONResponse?>?, response: Response<JSONResponse?>) {
//                val jsonResponse: JSONResponse? = response.body()
//                mArrayList = ArrayList<Any?>(Arrays.asList(jsonResponse.getAndroid()))
//                mAdapter = mArrayList?.let { DataAdapter(it) }
//                mRecyclerView?.setAdapter(mAdapter)
//            }
//
//            override fun onFailure(call: Call<JSONResponse?>?, t: Throwable) {
//                Log.d("Error", t.message!!)
//            }
//        })
//    }



//     private fun LoadJSON() {
//         var recyclerView: RecyclerView? = null
//         var layoutManager: RecyclerView.LayoutManager? = null
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
//            recyclerView!!.adapter = Adapter(it.Countries, this)
//        }

//        String country = Utils.getCountry();
//
//        Call<CovidAPI> call;
//        Call = CovidAPI.getAllData(Country, country);
//        Call.enqueue(new Call.Callback<Covid>(){


//            @Override
//            public void onResponse(Call<CovidAPI> call, Response<Country> response) {
//                    if (!Country.isEmpty()) {
//                        Country.clear();
//                    }
//
//                    country = response.body().getCountry();
//                    adapter = new Adapter(country, MainActivity.this);
//                    recyclerView?.setAdapter();
//                    adapter.notifyDataSetChanged();
//            }
//            else {
//                             Toast.makeText(MainActivity.this,"No Result!", Toast.LENGTH_SHORT).show();
//                         }
//
//
//                     @Override
//                             public void onFailure(Call<Covid> call, Throwable t) {
//
//        }


//    });
//}
//
//
//
//
//
//}

//
//        var recyclerView: RecyclerView? = null
//        val searchBar = findViewById<SearchView>(R.id.search_bar)




//        search()

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
//            recyclerView!!.adapter = Adapter(it.Countries, this)
//        }




//RxTextView.textChangeEvents(inputSearch)

//.map(new Func1<TextViewTextChangeEvent, String>() {
//    @Override
//    public String call(TextViewTextChangeEvent textViewTextChangeEvent) {
//        return textViewTextChangeEvent.text().toString()
//    }
//})
//.switchMap(new Func1<String, Observable<List<Result>>>() {
//    @Override
//    public Observable < List < Result > > call(String s) {
//        DiscoverAPI discoverAPI = restAdapter . create DiscoverAPI.class)
//        return discoverAPI.getRxSearchResult(0, 9, s)
//    }
//})
//.observeOn(AndroidSchedulers.mainThread())
//.subscribe(getRxSearchObserver())
//
//
//private Observer<List<Data>> getRxSearchObserver() {
//    return new Observer < List < Result > > () {
//        @Override
//        public void onCompleted() {
//            LogUtil.i(TAG, "onCompleted called")
//        }
//
//        @Override
//        public void onError(Throwable e) {
//            LogUtil.i(TAG, "onError called")
//        }
//
//        @Override
//        public void onNext(List<Data> results) {
//            LogUtil.i(TAG, "onNext called")
//            list.clear()
//            if (adapter != null) {
//                adapter.notifyDataSetInvalidated()
//            }
//            list.addAll(results)
//            if (adapter == null) {
//                adapter = new SearchGalleryAdapter TestActivity.this, list)
//                listView.setAdapter(adapter)
//            } else {
//                adapter.notifyDataSetChanged()
//            }
//            indicator.setVisibility(View.GONE)
//        }
//    }
//}
