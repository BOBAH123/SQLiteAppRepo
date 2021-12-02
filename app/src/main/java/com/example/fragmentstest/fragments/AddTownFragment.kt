package com.example.fragmentstest.fragments

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.fragmentstest.MainActivity
import com.example.fragmentstest.R
import com.example.fragmentstest.dbHelpers.DBHelper
import com.example.fragmentstest.dbHelpers.TownDBHelper

class AddTownFragment : Fragment() {
    private var data: Int? = null
    private lateinit var townName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getInt("data")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_town, container, false)
        val title = view.findViewById<TextView>(R.id.titleDetail)
        townName = view.findViewById(R.id.town_name_text)
        showCountryName(title)

        view.findViewById<Button>(R.id.btn_add_town).setOnClickListener {
            addTown()
        }

        view.findViewById<Button>(R.id.btn_show_town_list).setOnClickListener {
            (context as MainActivity).navController.navigate(
                R.id.action_detailsFragment_to_townsListFragment,
                bundleOf("code" to data)
            )
        }

        view.findViewById<Button>(R.id.btn_show_list).setOnClickListener {
            (context as MainActivity).navController.navigate(R.id.action_detailsFragment_to_mainFragment)
        }

        return view
    }

    @SuppressLint("Range")
    private fun showCountryName(title: TextView) {
        val cursor = (context as MainActivity).dbHelper.readableDatabase
            .query(
                DBHelper.KEY_DB_NAME, null,
                "_id = $data", null,
                null, null, null
            )
        if (cursor.moveToFirst()) {
            title.text = cursor.getString(cursor.getColumnIndex(DBHelper.KEY_NAME))
        }
        cursor.close()
        (context as MainActivity).dbHelper.close()
    }

    private fun addTown() {
        if (!townName.text.isEmpty()) {
            val contentValues = ContentValues()
            contentValues.put(
                TownDBHelper.KEY_NAME, townName.text.toString()
            )
            contentValues.put(
                TownDBHelper.KEY_COUNTRY_ID, data
            )
            (context as MainActivity).townDBHelper
                .writableDatabase
                .insert(TownDBHelper.KEY_DB_NAME, null, contentValues)
            (context as MainActivity).townDBHelper.close()
            townName.text.clear()
        } else {
            Toast.makeText(context, "Enter town name!", Toast.LENGTH_LONG).show()
        }
    }
}