package com.example.fragmentstest.rvAdapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentstest.Model.DataModel
import com.example.fragmentstest.R

class CountriesRecyclerViewAdapter(
    val dataList: List<DataModel>,
    val onClickListener: ClickListener
) : RecyclerView.Adapter<CountriesRecyclerViewAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textTitle.text = dataList[position].title
        holder.itemView.setOnClickListener {
            onClickListener.onClick(dataList[position])
        }
        holder.buttonEdit.setOnClickListener {
            onClickListener.onClick(dataList[position].id)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textTitle: TextView = view.findViewById(R.id.title_text)
        var buttonEdit: Button = view.findViewById(R.id.btn_add_details)
    }

    interface ClickListener {
        fun onClick(dataModel: DataModel)
        fun onClick(countryId: Int)
    }
}