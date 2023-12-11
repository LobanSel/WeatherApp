package com.example.weatherapp.Room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Converters {
    @TypeConverter
    fun fromList(value: List<ForecastDay>): String {
        val gson = Gson()
        return gson.toJson(value)
    }

    @TypeConverter
    fun toList(value: String): List<ForecastDay> {
        val gson = Gson()
        val listType = object : TypeToken<List<ForecastDay>>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromHourList(hourList: List<Hour>): String {
        return Gson().toJson(hourList)
    }

    @TypeConverter
    fun toHourList(hourListString: String): List<Hour> {
        val type = object : TypeToken<List<Hour>>() {}.type
        return Gson().fromJson(hourListString, type)
    }
}