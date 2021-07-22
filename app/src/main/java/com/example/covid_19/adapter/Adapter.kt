package com.example.covid_19.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.covid_19.R
import com.example.covid_19.models.Data
import com.example.covid_19.ui.DetailActivity
import com.squareup.picasso.Picasso
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList


class Adapter (private var postList: ArrayList<Data>, private val context: Context) :
    RecyclerView.Adapter<ViewHolder>(), Filterable {

//    private var postList = arrayListOf<Data>()
    var countryFilterList = arrayListOf<Data>()

    var txtNewConfirmed: TextView? = null
    var txtTotalConfirmed: TextView? = null
    var txtNewDeaths: TextView? = null
    var txtTotalDeaths: TextView? = null
    var txtNewRecovered: TextView? = null
    var txtTotalRecovered: TextView? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.country_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.BindTextValue!!.text = postList[position].country
        holder.BindContentValue!!.text = postList[position].countryCode
        holder.BindCardView!!.setOnClickListener {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("code", postList[position].countryCode)
            intent.putExtra("name", postList[position].country)

            val newCfString: String = postList[position].newConfirmed.toString()
            val totalCfString: String = postList[position].totalConfirmed.toString()
            val newDtString: String = postList[position].newDeaths.toString()
            val totalDtString: String = postList[position].totalDeaths.toString()
            val newReString: String = postList[position].newRecovered.toString()
            val totalReString: String = postList[position].totalRecovered.toString()
            val longval: Long


//            if ((newCfString).contains(",")) {
//                newCfString = newCfString.replace(",".toRegex(), "")
//            }
//            longval = newCfString.toLong()

//            val decim = DecimalFormat("#,###.##")
//            newCfString.setText(decim.format(newConfirmed))
//
//            newCfString.setText(java.lang.String.format(Locale.getDefault(), "%,d", newConfirmed))

//            NumberFormat.getInstance().format(newCfString)
            intent.putExtra("newConfirmed", newCfString)
            intent.putExtra("totalConfirmed", totalCfString)
            intent.putExtra("newDeaths", newDtString)
            intent.putExtra("totalDeaths", totalDtString)
            intent.putExtra("newRecovered", newReString)
            intent.putExtra("totalRecovered", totalReString)

//            val formatter: DecimalFormat = NumberFormat.getInstance(Locale.US) as DecimalFormat
//            formatter.applyPattern("#,###,###,###")
//            val formattedString: String = formatter.format(longval)


//                intent.putExtra("newConfirmed", postList[position].newConfirmed.toString())
//            intent.putExtra("totalConfirmed", postList[position].totalConfirmed.toString())
//            intent.putExtra("newDeaths", postList[position].newDeaths.toString())
//            intent.putExtra("totalDeaths", postList[position].totalDeaths.toString())
//            intent.putExtra("newRecovered", postList[position].newRecovered.toString())
//            intent.putExtra("totalRecovered", postList[position].totalRecovered.toString())

            intent.putExtra("date", postList[position].date)
            intent.putExtra("time", postList[position].date)
            context.startActivity(intent)
        }
        Picasso.get().load(postList[position].date)
            .error(R.drawable.ic_baseline_arrow_forward_ios_24)
            .placeholder(R.drawable.ic_baseline_arrow_forward_ios_24)
            .into(holder.BindImageView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    countryFilterList = postList
                } else {
                    val resultList = arrayListOf<Data>()
                    for (row in postList) {
                        if (row.country.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    countryFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = countryFilterList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                countryFilterList = results?.values as ArrayList<Data>
                notifyDataSetChanged()
            }
        }
    }
}



//    fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(charSequence: CharSequence): FilterResults {
//                val charString = charSequence.toString()
//                val mArrayList = postList
//                if (charString.isEmpty()) {
//                    postList = mArrayList
//                } else {
//                    val filteredList: ArrayList<Data> = ArrayList()
//                    val mArrayList: ArrayList<Data>
//                    for (country in postList) {
//                        if (country.toString().lowercase(Locale.getDefault())
//                                .contains(charString) || country.toString()
//                                .lowercase(Locale.getDefault())
////                                .contains(charString) || countryCode.toS.toLowerCase()
//                                .contains(charString)
//                        ) {
//                            filteredList.add(country)
//                        }
//                    }
//                    postList = filteredList
//                }
//                val filterResults = FilterResults()
//                filterResults.values = postList
//                return filterResults
//            }
//
//            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
//                postList = filterResults.values as ArrayList<Data>
//                notifyDataSetChanged()
//            }
//        }
//    }




//    fun afterTextChanged(s: TextView) {
//        try {
//            txtNewConfirmed?.removeTextChangedListener(this)
//            val value: String = txtNewConfirmed?.getText().toString()
//            if (value != null && value != "") {
//                if (value.startsWith(".")) {
//                    txtNewConfirmed?.setText("0.")
//                }
//                if (value.startsWith("0") && !value.startsWith("0.")) {
//                    txtNewConfirmed?.setText("")
//                }
//                val str: String = txtNewConfirmed?.getText().toString().replaceAll(",", "")
//                if (value != "") txtNewConfirmed?.setText(getDecimalFormattedString(str))
//                txtNewConfirmed.setSelection(txtNewConfirmed?.getText().toString().length)
//            }
//            txtNewConfirmed?.addTextChangedListener(this)
//            return
//        } catch (ex: Exception) {
//            ex.printStackTrace()
//            txtNewConfirmed?.addTextChangedListener(this)
//        }
//    }
//
//    fun getDecimalFormattedString(value: String): String? {
//        val lst = StringTokenizer(value, ".")
//        var str1 = value
//        var str2 = ""
//        if (lst.countTokens() > 1) {
//            str1 = lst.nextToken()
//            str2 = lst.nextToken()
//        }
//        var str3 = ""
//        var i = 0
//        var j = -1 + str1.length
//        if (str1[-1 + str1.length] == '.') {
//            j--
//            str3 = "."
//        }
//        var k = j
//        while (true) {
//            if (k < 0) {
//                if (str2.length > 0) str3 = "$str3.$str2"
//                return str3
//            }
//            if (i == 3) {
//                str3 = ",$str3"
//                i = 0
//            }
//            str3 = str1[k].toString() + str3
//            i++
//            k--
//        }
//    }
//
//    fun trimCommaOfString(string: String): String? {
////        String returnString;
//        return if (string.contains(",")) {
//            string.replace(",", "")
//        } else {
//            string
//        }
//    }
//}



//    fun afterTextChanged(s: Editable) {
//        editText.removeTextChangedListener(this)
//        try {
//            var originalString = s.toString()
//            val longval: Long
//            if (originalString.contains(",")) {
//                originalString = originalString.replace(",".toRegex(), "")
//            }
//            longval = originalString.toLong()
//            val formatter = NumberFormat.getInstance(Locale.US) as DecimalFormat
//            formatter.applyPattern("#,###,###,###")
//            val formattedString = formatter.format(longval)
//
//            //setting text after format to EditText
//            editText.setText(formattedString)
//            editText.setSelection(editText.getText().length())
//        } catch (nfe: NumberFormatException) {
//            nfe.printStackTrace()
//        }
//        editText.addTextChangedListener(this)
//    }



//    fun afterTextChanged(view: Editable) {
//        var s: String? = null
//        try {
//            s = String.format("%,d", view.toString().toLong())
//        } catch (e: NumberFormatException) {
//        }
//    }


