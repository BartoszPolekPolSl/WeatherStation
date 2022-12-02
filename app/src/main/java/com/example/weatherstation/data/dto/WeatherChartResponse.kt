package com.example.weatherstation.data.dto

import com.google.gson.annotations.SerializedName

data class WeatherChartResponse(
    @SerializedName("weatherChart")
    val weatherChart: List<Pair<Int, Float>>
)