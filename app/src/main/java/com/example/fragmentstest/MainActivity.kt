package com.example.fragmentstest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.fragmentstest.dbHelpers.DBHelper
import com.example.fragmentstest.dbHelpers.TownDBHelper

class MainActivity : AppCompatActivity() {

    lateinit var navController: NavController
    lateinit var dbHelper: DBHelper
    lateinit var townDBHelper: TownDBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHelper = DBHelper(this)
        townDBHelper = TownDBHelper(this)

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)
    }
}