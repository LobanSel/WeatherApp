package com.example.weatherapp.Room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "ForecastDay")
data class ForecastDay(
    @SerializedName("date")
    @PrimaryKey
    val date: String,
    val hour: List<Hour>
)
@Entity(tableName = "Hour")
data class Hour(
    @SerializedName("time")
    @PrimaryKey
    val time: String,
    @SerializedName("temp_c")
    val temp_c: Double
)

@Entity(tableName = "CurrentData")
data class CurrentData(
    @SerializedName("temp_c")
    @PrimaryKey
    val temp_c: Double,
    @SerializedName("condition")
    val condition : String
)