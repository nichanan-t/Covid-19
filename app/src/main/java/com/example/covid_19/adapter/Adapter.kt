package com.example.covid_19.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.R
import com.example.covid_19.models.Data
import com.example.covid_19.ui.DetailActivity
import java.text.NumberFormat

class Adapter (private var postList: ArrayList<Data>, private val context: Context) :
    RecyclerView.Adapter<ViewHolder>() {

    private var numFormat: NumberFormat = NumberFormat.getInstance()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.country_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.bindTextValue?.text = postList[position].country
        holder.bindContentValue?.text = postList[position].countryCode
        holder.bindImageView?.setImageResource(R.drawable.ic_baseline_arrow_forward_ios_24)
        holder.bindCardView?.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("code", postList[position].countryCode)
            intent.putExtra("name", postList[position].country)

            val newCfString: Int = postList[position].newConfirmed
            val newCf = numFormat.format(newCfString)

            val totalCfString: Int = postList[position].totalConfirmed
            val totalCf = numFormat.format(totalCfString)

            val newDtString: Int = postList[position].newDeaths
            val newDt = numFormat.format(newDtString)

            val totalDtString: Int = postList[position].totalDeaths
            val totalDt = numFormat.format(totalDtString)

            val newReString: Int = postList[position].newRecovered
            val newRe = numFormat.format(newReString)

            val totalReString: Int = postList[position].totalRecovered
            val totalRe = numFormat.format(totalReString)

            intent.putExtra("newConfirmed", newCf)
            intent.putExtra("totalConfirmed", totalCf)
            intent.putExtra("newDeaths", newDt)
            intent.putExtra("totalDeaths", totalDt)
            intent.putExtra("newRecovered", newRe)
            intent.putExtra("totalRecovered", totalRe)
            intent.putExtra("date", postList[position].date)
            intent.putExtra("time", postList[position].date)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}