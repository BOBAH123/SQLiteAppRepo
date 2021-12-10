package com.example.fragmentstest.rvAdapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentstest.Model.DetailsModel
import com.example.fragmentstest.R

class DetailsRVAdapter: RecyclerView.Adapter<DetailsRVAdapter.MyViewHolder>() {

    var dataList = emptyList<DetailsModel>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<DetailsModel>) {
        dataList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.details_card_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textCapital.text = dataList[position].capital?: "No data"
        holder.textRegion.text = dataList[position].region?: "No data"
        holder.textCurrency.text = dataList[position].currency?: "No data"
        holder.textPopulation.text = dataList[position].population?.plus(" M") ?:  "No data"
        holder.textLanguage.text = dataList[position].language?: "No data"
        holder.textCountry.text = dataList[position].country
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textCapital: TextView = view.findViewById(R.id.capital_text)
        var textRegion: TextView = view.findViewById(R.id.region_text)
        var textCurrency: TextView = view.findViewById(R.id.currency_text)
        var textCountry: TextView = view.findViewById(R.id.country_text)
        var textPopulation: TextView = view.findViewById(R.id.population_text)
        var textLanguage: TextView = view.findViewById(R.id.language_text)
    }
}