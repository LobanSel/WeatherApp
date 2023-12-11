package com.example.weatherapp.API.DataClass

import com.example.weatherapp.Room.ForecastDay

data class ApiForecast(
    val forecast: Forecast
)
data class Forecast(
    val forecastday: List<ForecastDay>
)

data class ForecastDay(
    val date: String,
    val hour: List<ApiHour>
)

data class ApiHour(
    val time: String,
    val temp_c: Double,
)
