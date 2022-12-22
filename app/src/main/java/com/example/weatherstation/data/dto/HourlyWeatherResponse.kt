package com.example.weatherstation.data.dto

import com.google.gson.annotations.SerializedName

data class HourlyWeather(
    @SerializedName("time")
    val time: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("temperature")
    val temperature: String
)