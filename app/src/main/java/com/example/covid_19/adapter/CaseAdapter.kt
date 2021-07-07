package com.example.covid_19.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.ui.DetailActivity
import com.example.covid_19.R
import com.example.covid_19.models.RecyclerData

class CaseAdapter (private val postList: List<RecyclerData>, private val context: Context) :
    RecyclerView.Adapter<ViewHolderCase>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCase {
        return ViewHolderCase(LayoutInflater.from(context).inflate(R.layout.covid_info, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderCase, position: Int) {
        holder.BindNewCF!!.text = postList[position].NewConfirmed.toString()
        holder.BindTotalCF!!.text = postList[position].TotalConfirmed.toString()
        holder.BindNewD!!.text = postList[position].NewDeaths.toString()
        holder.BindTotalD!!.text = postList[position].TotalDeaths.toString()
        holder.BindNewR!!.text = postList[position].NewRecovered.toString()
        holder.BindTotalR!!.text = postList[position].TotalRecovered.toString()
        holder.BindCardv!!.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("Code", postList[position].CountryCode)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}