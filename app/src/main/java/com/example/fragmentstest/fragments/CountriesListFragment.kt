package com.example.fragmentstest.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentstest.MainActivity
import com.example.fragmentstest.Model.DataModel
import com.example.fragmentstest.R
import com.example.fragmentstest.dbHelpers.DBHelper
import com.example.fragmentstest.rvAdapters.CountriesRecyclerViewAdapter


class CountriesListFragment : Fragment(), CountriesRecyclerViewAdapter.ClickListener {

    private lateinit var adapterCountries: CountriesRecyclerViewAdapter
    lateinit var dataList: ArrayList<DataModel>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_countries_list, container, false)
        dataList = ArrayList()
        buildDisplayData()
        initRV(view)
        view.findViewById<Button>(R.id.btn_add_country).setOnClickListener {
            (context as MainActivity).navController.navigate(R.id.action_mainFragment_to_addCountryFragment)
        }

        view.findViewById<Button>(R.id.btn_show_details).setOnClickListener {
            (context as MainActivity).navController.navigate(R.id.action_mainFragment_to_townsListFragment)
        }
        return view
    }

    private fun initRV(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.main_rv)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        adapterCountries = CountriesRecyclerViewAdapter(dataList, this)
        recyclerView.adapter = adapterCountries
    }

    @SuppressLint("Range")
    private fun buildDisplayData() {
        val sqLiteDatabase = (context as MainActivity).dbHelper.readableDatabase
        val cursor = sqLiteDatabase.query(DBHelper.KEY_DB_NAME, null, null, null, null, null, null)
        if (cursor.moveToFirst()) {
            do {
                dataList.add(
                    DataModel(
                        cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ID)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NAME))
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        (context as MainActivity).dbHelper.close()
    }

    override fun onClick(dataModel: DataModel) {
        (context as MainActivity).navController.navigate(
            R.id.action_mainFragment_to_townsListFragment,
            bundleOf("code" to dataModel.id)
        )
    }

    override fun onClick(countryId: Int) {
        (context as MainActivity).navController.navigate(
            R.id.action_mainFragment_to_detailsFragment,
            bundleOf("code" to countryId)
        )
    }
}