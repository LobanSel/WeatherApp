package com.example.weatherapp.API

import com.example.weatherapp.API.DataClass.ApiForecast
import com.example.weatherapp.API.DataClass.CurrentData
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("current.json")
    suspend fun getCurrentData(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("aqi") aqi: String,
        @Query("lang") lang: String
    ): CurrentData
    @GET("forecast.json")
    suspend fun getForecast(
        @Query("key") apiKey: String,
        @Query("q") location: String,
        @Query("days") days: Int,
        @Query("aqi") aqi:String,
        @Query("alerts") alerts:String
    ): ApiForecast

}