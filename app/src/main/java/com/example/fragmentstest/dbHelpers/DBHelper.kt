package com.example.fragmentstest.dbHelpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context): SQLiteOpenHelper(context, KEY_DB_NAME,null, 3) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table $KEY_DB_NAME($KEY_ID integer primary key, $KEY_NAME text, $KEY_CAPITAL_NAME text," +
                " $KEY_REGION_NAME text, $KEY_CURRENCY text, $KEY_POPULATION float, $KEY_LANGUAGE text)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("drop table if exists $KEY_DB_NAME")
        onCreate(db)
    }

    companion object {
        val KEY_ID = "_id"
        val KEY_NAME = "country_name"
        val KEY_CAPITAL_NAME = "capital_name"
        val KEY_REGION_NAME = "region_name"
        val KEY_CURRENCY = "currency"
        val KEY_POPULATION = "population"
        val KEY_LANGUAGE = "language"
        val KEY_DB_NAME = "countries"
    }
}