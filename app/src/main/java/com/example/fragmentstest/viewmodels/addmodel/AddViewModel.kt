package com.example.fragmentstest.viewmodels.addmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fragmentstest.Model.DetailsModel
import com.example.fragmentstest.REPOSITORY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddViewModel : ViewModel() {
    fun addCountry(countryModel: DetailsModel, onSuccess: () -> Unit) {
        viewModelScope.launch(Dispatchers.IO) {
            REPOSITORY.addCountry(countryModel) {
                onSuccess()
            }
        }
    }

    fun getCountryById(countryId: Int): LiveData<DetailsModel> {
        return REPOSITORY.getCountryById(countryId)
    }
}