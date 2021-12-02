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
import com.example.fragmentstest.R
import com.example.fragmentstest.dbHelpers.TownDBHelper
import com.example.fragmentstest.rvAdapters.TownsRVAdapter

class TownsListFragment : Fragment() {
    private var countryId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            countryId = it.getInt("code")
        }
    }

    private lateinit var townsAdapter: TownsRVAdapter
    val dataList: ArrayList<String> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_towns_list, container, false)
        buildDisplayData()
        initRV(view)
        return view
    }

    private fun initRV(view: View) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.towns_rv)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        townsAdapter = TownsRVAdapter(dataList)
        recyclerView.adapter = townsAdapter
    }

    @SuppressLint("Range")
    private fun buildDisplayData() {
        val cursor = (context as MainActivity).townDBHelper.readableDatabase
            .query(
                TownDBHelper.KEY_DB_NAME, null,
                "${TownDBHelper.KEY_COUNTRY_ID} = $countryId", null, null,
                null, null
            )
        if (cursor.moveToFirst()) {
            do {
                dataList.add(
                    cursor.getString(cursor.getColumnIndex(TownDBHelper.KEY_NAME))
                )
            } while (cursor.moveToNext())
        }
        cursor.close()
        (context as MainActivity).townDBHelper.close()
    }

}