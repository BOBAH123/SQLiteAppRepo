package com.example.fragmentstest.database.repository

import androidx.lifecycle.LiveData
import com.example.fragmentstest.Model.DetailsModel

interface CountryRepository {
    val allCountries: LiveData<List<DetailsModel>>
    fun getCountryById(countryId: Int): LiveData<DetailsModel>
    suspend fun addCountry(countryModel: DetailsModel, onSuccess:() -> Unit)
}