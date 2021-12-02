package com.example.fragmentstest.dbHelpers

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class TownDBHelper(context: Context) : SQLiteOpenHelper(context, KEY_DB_NAME, null, 2) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(
            "create table $KEY_DB_NAME($KEY_ID integer primary key, $KEY_NAME text," +
                    " $KEY_COUNTRY_ID integer, foreign key ($KEY_COUNTRY_ID) references countries(_id))"
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {
        db?.execSQL("drop table if exists $KEY_DB_NAME")
        onCreate(db)
    }

    companion object {
        val KEY_ID = "_id"
        val KEY_NAME = "town_name"
        val KEY_COUNTRY_ID = "country_id"
        val KEY_DB_NAME = "towns"
    }
}