package com.example.fragmentstest.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fragmentstest.MainActivity
import com.example.fragmentstest.Model.DetailsModel
import com.example.fragmentstest.R
import com.example.fragmentstest.dbHelpers.DBHelper
import com.example.fragmentstest.rvAdapters.DetailsRVAdapter

class DetailsListFragment : Fragment() {
    private var countryId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            countryId = it.getInt("code")
        }
    }

    private lateinit var detailsAdapter: DetailsRVAdapter
    val dataList: ArrayList<DetailsModel> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_details_list, container, false)
        buildDisplayData()
        initRV(view)
        return view
    }

    private fun initRV(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.towns_rv)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        detailsAdapter = DetailsRVAdapter(dataList)
        recyclerView.adapter = detailsAdapter
    }

    @SuppressLint("Range")
    private fun buildDisplayData() {
        val cursor =
            if (countryId != 0) {
            (context as MainActivity).dbHelper.readableDatabase
                .query(
                    DBHelper.KEY_DB_NAME, null,
                    "${DBHelper.KEY_ID} = $countryId", null, null,
                    null, null
                )
        } else {
            (context as MainActivity).dbHelper.readableDatabase
                .query(
                    DBHelper.KEY_DB_NAME, null,
                    null, null, null,
                    null, null
                )
        }
        if (cursor.moveToFirst()) {
            do {
                dataList.add(
                    DetailsModel(
                        cursor.getInt(cursor.getColumnIndex(DBHelper.KEY_ID)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NAME)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.KEY_CAPITAL_NAME)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.KEY_REGION_NAME)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.KEY_CURRENCY)),
                        cursor.getFloat(cursor.getColumnIndex(DBHelper.KEY_POPULATION)),
                        cursor.getString(cursor.getColumnIndex(DBHelper.KEY_LANGUAGE))
                    )
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        (context as MainActivity).dbHelper.close()
    }

}