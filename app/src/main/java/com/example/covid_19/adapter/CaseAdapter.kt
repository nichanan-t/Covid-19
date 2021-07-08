package com.example.covid_19.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.R
import com.example.covid_19.models.Data
import com.example.covid_19.ui.DetailActivity

class CaseAdapter (private val postList: List<Data>, private val context: Context) :
    RecyclerView.Adapter<ViewHolderCase>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderCase {
        return ViewHolderCase(LayoutInflater.from(context).inflate(R.layout.covid_info, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolderCase, position: Int) {
        holder.BindNewCF!!.text = postList[position].newConfirmed.toString()
        holder.BindTotalCF!!.text = postList[position].totalConfirmed.toString()
        holder.BindNewD!!.text = postList[position].newDeaths.toString()
        holder.BindTotalD!!.text = postList[position].totalDeaths.toString()
        holder.BindNewR!!.text = postList[position].newRecovered.toString()
        holder.BindTotalR!!.text = postList[position].totalRecovered.toString()
        holder.BindCardv!!.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("Code", postList[position].countryCode)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return postList.size
    }
}