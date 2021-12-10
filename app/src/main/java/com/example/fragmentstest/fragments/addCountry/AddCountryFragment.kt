package com.example.fragmentstest.fragments.addCountry

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.fragmentstest.MainActivity
import com.example.fragmentstest.Model.DetailsModel
import com.example.fragmentstest.R
import com.example.fragmentstest.databinding.FragmentAddCountryBinding
import com.example.fragmentstest.viewmodels.addmodel.AddViewModel

class AddCountryFragment : Fragment() {
    private lateinit var binding: FragmentAddCountryBinding

    @SuppressLint("Range")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentAddCountryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(AddViewModel::class.java)
        binding.btnAdd.setOnClickListener {
            viewModel.addCountry(
                DetailsModel(country = binding.countryNameText.text.toString())
            ) { }
            (context as MainActivity).navController.navigate(R.id.action_addCountryFragment_to_mainFragment)
        }
    }
}