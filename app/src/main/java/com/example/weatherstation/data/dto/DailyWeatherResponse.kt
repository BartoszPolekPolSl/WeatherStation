package com.example.weatherstation.data.dto

import com.google.gson.annotations.SerializedName

data class DailyWeatherResponse(
    @SerializedName("is_raining")
    val isRaining: Int?,
    @SerializedName("temperature")
    val temperature: Float?,
    @SerializedName("pressure")
    val pressure: Float?,
    @SerializedName("humidity")
    val humidity: Float?
)