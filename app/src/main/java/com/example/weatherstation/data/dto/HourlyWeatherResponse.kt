package com.example.weatherstation.data.dto

import com.google.gson.annotations.SerializedName

data class HourlyWeathersResponse(
    @SerializedName("hourlyWeathers")
    val hourlyWeather: List<HourlyWeather>
)

data class HourlyWeather(
    @SerializedName("time")
    val time: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("temperature")
    val temperature: String
)