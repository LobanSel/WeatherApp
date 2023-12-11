package com.example.weatherapp.Room

import android.util.Log
import com.example.weatherapp.API.ApiManager
import com.example.weatherapp.API.DataClass.Forecast
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DBManager @Inject constructor(weatherDatabase: WeatherDatabase) {
    private val apiManager = ApiManager()
    private var weatherDatabase: WeatherDatabase

    init {
        this.weatherDatabase = weatherDatabase
    }

    fun dataGet() {
        Log.d("DataGet", "Started")
        CoroutineScope(Dispatchers.IO + CoroutineName("1")).launch {
            try {
                val apiForecast = apiManager.getForecast()
                val apiCurrentData = apiManager.getCurrentData()
                withContext(Dispatchers.IO) {
                    val forecast = Forecast(
                        forecastday = apiForecast.forecast.forecastday.map { apiForecastDay ->
                            ForecastDay(
                                date = apiForecastDay.date,
                                hour = apiForecastDay.hour.map { apiHour ->
                                    Hour(
                                        time = apiHour.time,
                                        temp_c = apiHour.temp_c
                                    )
                                }
                            )
                        }
                    )
                    val currentData = CurrentData(
                        temp_c = apiCurrentData.current.temp_c,
                        condition = apiCurrentData.current.condition.text
                    )

                    weatherDatabase.RoomDao().insertCurrentData(currentData)

                    for (forecastDay in forecast.forecastday) {
                        weatherDatabase.RoomDao().insertForecastDay(forecastDay)
                        for (hour in forecastDay.hour) {
                            val hourData = Hour(
                                time = hour.time,
                                temp_c = hour.temp_c
                            )
                            weatherDatabase.RoomDao().insertHour(hourData)
                        }
                    }

                }

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

    /*fun queryData() {
        CoroutineScope(Dispatchers.IO).launch {
            val dataWithHourlyData = weatherDatabase.RoomDao().getDateWithHourlyData("11")
            Log.d("QueryData", "Data with hourly data: $dataWithHourlyData")
        }
    }*/