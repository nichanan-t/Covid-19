package com.example.covid_19.ui

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.covid_19.R

class DetailActivity : AppCompatActivity() {

    private var showNewConfirmed: TextView? = null
    private var showTotalConfirmed: TextView? = null
    private var showNewDeaths: TextView? = null
    private var showTotalDeaths: TextView? = null
    private var showNewRecovered: TextView? = null
    private var showTotalRecovered: TextView? = null
    private var showDate: TextView? = null
    private var showTime: TextView? = null
    var tb: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent

        tb = findViewById<Toolbar>(R.id.toolbar)
        tb?.title = intent.getStringExtra("name")
        setSupportActionBar(tb)

        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_ios_24)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        showNewConfirmed = findViewById(R.id.txtNewConfirmed)
        showTotalConfirmed = findViewById(R.id.txtTotalConfirmed)
        showNewDeaths = findViewById(R.id.txtNewDeaths)
        showTotalDeaths = findViewById(R.id.txtTotalDeaths)
        showNewRecovered = findViewById(R.id.txtNewRecovered)
        showTotalRecovered = findViewById(R.id.txtTotalRecovered)
        showDate = findViewById(R.id.date)
        showTime = findViewById(R.id.time)

        showNewConfirmed?.text = intent.getStringExtra("newConfirmed")
        showTotalConfirmed?.text = intent.getStringExtra("totalConfirmed")
        showNewDeaths?.text = intent.getStringExtra("newDeaths")
        showTotalDeaths?.text = intent.getStringExtra("totalDeaths")
        showNewRecovered?.text = intent.getStringExtra("newRecovered")
        showTotalRecovered?.text = intent.getStringExtra("totalRecovered")
        showDate?.text = intent.getStringExtra("date")?.substring(0,10)
        showTime?.text = intent.getStringExtra("date")?.substring(11,20)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}