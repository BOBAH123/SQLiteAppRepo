package com.example.fragmentstest.fragments.countriesList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentstest.MainActivity
import com.example.fragmentstest.Model.DetailsModel
import com.example.fragmentstest.R
import com.example.fragmentstest.databinding.FragmentCountriesListBinding
import com.example.fragmentstest.rvAdapters.CountriesRecyclerViewAdapter
import com.example.fragmentstest.viewmodels.listmodel.ListViewModel

class CountriesListFragment : Fragment(), CountriesRecyclerViewAdapter.ClickListener {

    private lateinit var adapterCountries: CountriesRecyclerViewAdapter
    private lateinit var binding: FragmentCountriesListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCountriesListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()

    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.initDatabase()
        val rv = binding.mainRv
        rv.layoutManager = LinearLayoutManager(activity)
        adapterCountries = CountriesRecyclerViewAdapter(this)
        rv.adapter = adapterCountries
        viewModel.getAllCountries().observe(viewLifecycleOwner, {
            adapterCountries.setList(it)
        })
        binding.btnAddCountry.setOnClickListener {
            (context as MainActivity).navController.navigate(R.id.action_mainFragment_to_addCountryFragment)
        }

        binding.btnShowDetails.setOnClickListener {
            (context as MainActivity).navController.navigate(R.id.action_mainFragment_to_townsListFragment)
        }
    }

    override fun onClick(dataModel: DetailsModel) {
        Log.i("code", dataModel.toString())
        (context as MainActivity).navController.navigate(
            R.id.action_mainFragment_to_townsListFragment,
            bundleOf("code" to dataModel.id)
        )
    }

    override fun onClick(countryId: Int) {
        Log.i("code", countryId.toString())
        (context as MainActivity).navController.navigate(
            R.id.action_mainFragment_to_detailsFragment,
            bundleOf("code" to countryId)
        )
    }
}