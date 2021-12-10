package com.example.fragmentstest.viewmodels.listmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.fragmentstest.Model.DetailsModel
import com.example.fragmentstest.REPOSITORY
import com.example.fragmentstest.database.CountryDataBase
import com.example.fragmentstest.database.repository.CountryRepositoryImpl

class ListViewModel(application: Application): AndroidViewModel(application) {

    val context = application

    fun initDatabase() {
        val database = CountryDataBase.getInstance(context).getCountryDao()
        REPOSITORY = CountryRepositoryImpl(database)
    }

    fun getAllCountries(): LiveData<List<DetailsModel>> {
        return REPOSITORY.allCountries
    }

    fun getCountryById(countryId: Int): LiveData<DetailsModel> {
        return REPOSITORY.getCountryById(countryId)
    }
}