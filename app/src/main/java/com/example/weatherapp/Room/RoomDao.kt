package com.example.weatherapp.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoomDao {
    @Insert
    suspend fun insertForecastDay(forecastDay: ForecastDay)

    @Insert
    suspend fun insertHour(hour: Hour)

    @Insert
    suspend fun insertCurrentData(currentData: CurrentData)

    @Query("SELECT * FROM Hour")
    suspend fun getHourData(): List<Hour>

    @Query("SELECT * FROM CurrentData")
    suspend fun getCurrentData(): CurrentData

}