package com.example.covid_19.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.R

class ViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)  {
    val bindTextValue: TextView? = itemView.findViewById(R.id.txtTitle)
    val bindContentValue: TextView? = itemView.findViewById(R.id.txtContent)
    val bindImageView: ImageView? = itemView.findViewById(R.id.select)
    val bindCardView: CardView? = itemView.findViewById(R.id.cardview)
}