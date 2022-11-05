package com.example.weatherstation.data.dto

import com.example.weatherstation.domain.weather.WeatherPresentationModel
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("time")
    val time: String,
    @SerializedName("temperature")
    val temperature: String,
    @SerializedName("pressure")
    val pressure: String,
    @SerializedName("humidity")
    val humidity: String,
    @SerializedName("rain")
    val rain: String
) {
    fun toPresentationModel(): WeatherPresentationModel {
        return WeatherPresentationModel(
            time = time,
            temperature = temperature,
            pressure = pressure,
            humidity = humidity,
            rain = rain
        )
    }
}