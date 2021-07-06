package com.example.covid_19.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.R
import com.example.covid_19.models.RecyclerData
import com.squareup.picasso.Picasso

class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    var items = ArrayList<RecyclerData>()

    fun setUpdatedData(items : ArrayList<RecyclerData>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
            var countryTitle = view.findViewById<TextView>(R.id.countryTitle)
            var countryCode = view.findViewById<TextView>(R.id.countryCode)

        fun bind(data : RecyclerData) {
                countryTitle.text = data.Country
                countryCode.text = data.CountryCode

            }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_list_row, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items.get(position))
    }

    override fun getItemCount(): Int {
        return items.size
    }

}