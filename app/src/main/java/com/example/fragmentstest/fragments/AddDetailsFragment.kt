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

class AddDetailsFragment : Fragment() {
    private var data: Int? = null
    private lateinit var capitalName: EditText
    private lateinit var region: EditText
    private lateinit var currency: EditText
    private lateinit var population: EditText
    private lateinit var language: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getInt("code")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_add_details, container, false)
        val title = view.findViewById<TextView>(R.id.titleDetail)
        capitalName = view.findViewById(R.id.capital_name_text)
        region = view.findViewById(R.id.region_name_text)
        currency = view.findViewById(R.id.currency_text)
        population = view.findViewById(R.id.population_text)
        language = view.findViewById(R.id.language_text)
        showCountryName(title)

        view.findViewById<Button>(R.id.btn_add_town).setOnClickListener {
            addDetails()
            (context as MainActivity).navController.navigate(R.id.action_detailsFragment_to_mainFragment)
        }

        view.findViewById<Button>(R.id.btn_show_town_list).setOnClickListener {
            (context as MainActivity).navController.navigate(
                R.id.action_detailsFragment_to_townsListFragment,
                bundleOf("code" to data)
            )
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

    private fun addDetails() {
        if (!(region.text.isEmpty() || currency.text.isEmpty() || population.text.isEmpty()
                    || language.text.isEmpty())) {
            val contentValues = ContentValues()
            contentValues.put(
                DBHelper.KEY_CAPITAL_NAME, capitalName.text.toString()
            )
            contentValues.put(
                DBHelper.KEY_REGION_NAME, region.text.toString()
            )
            contentValues.put(
                DBHelper.KEY_CURRENCY, currency.text.toString()
            )
            contentValues.put(
                DBHelper.KEY_POPULATION, population.text.toString()
            )
            contentValues.put(
                DBHelper.KEY_LANGUAGE, language.text.toString()
            )
            (context as MainActivity).dbHelper
                .writableDatabase
                .update(DBHelper.KEY_DB_NAME,  contentValues, "_id = ?", arrayOf("$data"))
            (context as MainActivity).dbHelper.close()
        } else {
            Toast.makeText(context, "Enter town name!", Toast.LENGTH_LONG).show()
        }
    }
}