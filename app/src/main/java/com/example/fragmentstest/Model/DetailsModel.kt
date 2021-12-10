package com.example.fragmentstest.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Country")
data class DetailsModel(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    @ColumnInfo
    val country: String,

    @ColumnInfo
    val capital: String? = null,

    @ColumnInfo
    val region: String? = null,

    @ColumnInfo
    val currency: String? = null,

    @ColumnInfo
    val population: String? = null,

    @ColumnInfo
    val language: String? = null
)