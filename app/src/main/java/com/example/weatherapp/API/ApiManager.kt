package com.example.weatherapp.API

import android.util.Log
import com.example.weatherapp.API.DataClass.ApiForecast
import com.example.weatherapp.API.DataClass.CurrentData
import com.example.weatherapp.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    val BASE_URL: String = "https://api.weatherapi.com/v1/"
    val apiService: ApiService
    val API_KEY = BuildConfig.API_KEY
    init {

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    suspend fun getCurrentData(): CurrentData {
        val currentData: CurrentData = apiService.getCurrentData(API_KEY,"Moscow","no","ru")
        Log.d("ApiManager", "Получены данные: $currentData")
        return currentData
    }
    suspend fun getForecast():ApiForecast
    {
        val apiForecast: ApiForecast = apiService.getForecast(API_KEY,"Moscow",2,"no","no")
        Log.d("ApiManager2","Forecast Data:$apiForecast")
        return apiForecast
    }
}
