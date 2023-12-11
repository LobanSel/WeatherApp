package com.example.weatherapp.Room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
@Database(entities = [ForecastDay::class,Hour::class,CurrentData::class], version = 1)
@TypeConverters(Converters::class)
abstract class WeatherDatabase:RoomDatabase() {
    abstract fun RoomDao(): RoomDao
}