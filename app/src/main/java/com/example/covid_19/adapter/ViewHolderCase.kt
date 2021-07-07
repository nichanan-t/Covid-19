package com.example.covid_19.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.view.menu.MenuView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.R

class ViewHolderCase (itemView: View) : RecyclerView.ViewHolder(itemView) {
    val BindNewCF: TextView? = itemView.findViewById(R.id.txtNewConfirmed)
    val BindTotalCF: TextView? = itemView.findViewById(R.id.txtTotalConfirmed)
    val BindNewD: TextView? = itemView.findViewById(R.id.txtNewDeaths)
    val BindTotalD: TextView? = itemView.findViewById(R.id.txtTotalDeaths)
    val BindNewR: TextView? = itemView.findViewById(R.id.txtNewRecovered)
    val BindTotalR: TextView? = itemView.findViewById(R.id.txtTotalRecovered)
    val BindCardv: CardView? = itemView.findViewById(R.id.cardView)
}
