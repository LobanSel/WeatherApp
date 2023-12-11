package com.example.weatherapp.API.DataClass

data class CurrentData(
    val current: Current
)
data class Current(
    val temp_c: Double,
    val condition:Conditiont

)

data class Conditiont(
    val text: String
)
