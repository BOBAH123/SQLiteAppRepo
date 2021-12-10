package com.example.fragmentstest.database.repository

import androidx.lifecycle.LiveData
import com.example.fragmentstest.Model.DetailsModel
import com.example.fragmentstest.database.dao.CountryDao

class CountryRepositoryImpl(private val countryDao: CountryDao): CountryRepository {
    override val allCountries: LiveData<List<DetailsModel>>
        get() = countryDao.getAllCountries()

    override fun getCountryById(countryId: Int): LiveData<DetailsModel> {
        return countryDao.getCountryById(countryId)
    }


    override suspend fun addCountry(countryModel: DetailsModel, onSuccess: () -> Unit) {
        countryDao.addCountry(countryModel)
        onSuccess()
    }
}