package com.example.covid_19.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.R
import com.example.covid_19.models.Data
import com.example.covid_19.ui.DetailActivity
import com.squareup.picasso.Picasso

class Adapter (private val postList: List<Data>, private val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.country_item, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.BindTextValue!!.text = postList[position].country
        holder.BindContentValue!!.text = postList[position].countryCode
        holder.BindCardView!!.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("Code", postList[position].countryCode)
            context.startActivity(intent)
        }
        Picasso.get().load(postList[position].date).error(R.drawable.ic_baseline_arrow_forward_ios_24)
            .placeholder(R.drawable.ic_baseline_arrow_forward_ios_24)
            .into(holder.BindImageView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

}