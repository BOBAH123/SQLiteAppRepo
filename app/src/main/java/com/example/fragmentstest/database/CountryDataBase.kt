package com.example.fragmentstest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fragmentstest.Model.DetailsModel
import com.example.fragmentstest.database.dao.CountryDao

@Database(entities = [DetailsModel::class], version = 1)
abstract class CountryDataBase : RoomDatabase() {
    abstract fun getCountryDao(): CountryDao

    companion object {
        private var database: CountryDataBase? = null

        @Synchronized
        fun getInstance(context: Context): CountryDataBase {
            return if (database == null) {
                database = Room.databaseBuilder(
                    context, CountryDataBase::class.java,
                    "db"
                ).build()
                database as CountryDataBase
            } else {
                database as CountryDataBase
            }
        }
    }
}