package com.example.fragmentstest.fragments.addDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fragmentstest.MainActivity
import com.example.fragmentstest.Model.DetailsModel
import com.example.fragmentstest.R
import com.example.fragmentstest.databinding.FragmentAddDetailsBinding
import com.example.fragmentstest.viewmodels.addmodel.AddViewModel

class AddDetailsFragment : Fragment() {
    private var data: Int? = null
    private lateinit var binding: FragmentAddDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            data = it.getInt("code")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(AddViewModel::class.java)
        binding.btnAddTown.setOnClickListener {
            viewModel.addCountry(
                DetailsModel(
                    id = data!!,
                    country = binding.titleDetail.text.toString(),
                    capital = binding.capitalNameText.text.toString(),
                    region = binding.regionNameText.text.toString(),
                    currency = binding.currencyText.text.toString(),
                    population = binding.populationText.text.toString(),
                    language = binding.languageText.text.toString()
                )
            ) { }
            (context as MainActivity).navController.navigate(R.id.action_detailsFragment_to_mainFragment)
        }

        binding.btnShowTownList.setOnClickListener {
            (context as MainActivity).navController.navigate(
                R.id.action_detailsFragment_to_townsListFragment,
                bundleOf("code" to data)
            )
        }
        viewModel.getCountryById(data!!).observe(viewLifecycleOwner, {
            binding.titleDetail.text = it.country
        })
    }
}