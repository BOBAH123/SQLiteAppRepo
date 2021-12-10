package com.example.fragmentstest.fragments.detailsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fragmentstest.databinding.FragmentDetailsListBinding
import com.example.fragmentstest.viewmodels.listmodel.ListViewModel
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

    private lateinit var binding: FragmentDetailsListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDetailsListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this).get(ListViewModel::class.java)
        viewModel.initDatabase()
        val rv = binding.townsRv
        rv.layoutManager = LinearLayoutManager(activity)
        detailsAdapter = DetailsRVAdapter()
        rv.adapter = detailsAdapter
        if (countryId != 0) {
            viewModel.getCountryById(countryId!!).observe(viewLifecycleOwner, {
                detailsAdapter.setList(listOf(it))
            })
        } else {
            viewModel.getAllCountries().observe(viewLifecycleOwner, {
                detailsAdapter.setList(it)
            })
        }
    }
}