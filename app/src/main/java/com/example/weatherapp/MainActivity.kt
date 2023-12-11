package com.example.weatherapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.weatherapp.Room.DBManager
import com.example.weatherapp.Room.WeatherDatabase
import com.example.weatherapp.screens.MainScreen
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var weatherDatabase: WeatherDatabase
    @Inject
    lateinit var dBManager : DBManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.e("MA", "BD created" + weatherDatabase)
        dBManager.dataGet()
        setContent {
            MainScreen()
        }
    }
}

