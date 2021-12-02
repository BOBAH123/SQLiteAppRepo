package com.example.fragmentstest.fragments

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.fragmentstest.MainActivity
import com.example.fragmentstest.R
import com.example.fragmentstest.dbHelpers.DBHelper

class AddCountryFragment : Fragment() {
    private lateinit var countryName: EditText

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_country, container, false)
        countryName = view.findViewById(R.id.country_name_text)
        view.findViewById<Button>(R.id.btn_add).setOnClickListener {
            addCountry()
        }

        view.findViewById<Button>(R.id.btn_show_all).setOnClickListener {
            (context as MainActivity).navController.navigate(R.id.action_addCountryFragment_to_mainFragment)
        }
        return view
    }

    private fun addCountry() {
        if (!countryName.text.isEmpty()) {
            val contentValues = ContentValues()
            contentValues.put(
                DBHelper.KEY_NAME, countryName.text.toString()
            )
            (context as MainActivity).dbHelper.writableDatabase.insert(
                DBHelper.KEY_DB_NAME,
                null,
                contentValues
            )
            (context as MainActivity).dbHelper.close()
            countryName.text.clear()
        } else {
            Toast.makeText(activity, "Enter country name!", Toast.LENGTH_LONG).show()
        }
    }
}