package com.example.weatherapp.ViewModel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.Room.Hour
import com.example.weatherapp.Room.WeatherDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(weatherDatabase: WeatherDatabase) : ViewModel(){

    val currentWeatherLiveData = MutableLiveData<com.example.weatherapp.Room.CurrentData>()
    var hoursLiveData = MutableLiveData<List<Hour>>()


    init {
        viewModelScope.launch(Dispatchers.IO) {
                try {
                    withContext(Dispatchers.Main) {
                        currentWeatherLiveData.value = weatherDatabase.RoomDao().getCurrentData()
                        hoursLiveData.value = weatherDatabase.RoomDao().getHourData()


                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main)
                    {
                        Log.e("MVMD", "Error: ${e.message}", e)
                    }
                }

            }
        }



    }