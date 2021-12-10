package com.example.fragmentstest.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.fragmentstest.Model.DetailsModel

@Dao
interface CountryDao {

    @Query("SELECT * FROM country")
    fun getAllCountries(): LiveData<List<DetailsModel>>

    @Query("SELECT * FROM country WHERE id = :countyId")
    fun getCountryById(countyId: Int): LiveData<DetailsModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCountry(countryModel: DetailsModel)
}