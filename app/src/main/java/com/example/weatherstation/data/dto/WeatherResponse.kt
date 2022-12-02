package com.example.weatherstation.data.dto

import com.example.weatherstation.data.model.weather.WeatherPresentationModel
import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    @SerializedName("station_id")
    val stationId: Int,
    @SerializedName("station_name")
    val stationName: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("temperature")
    val temperature: String?,
    @SerializedName("pressure")
    val pressure: Float?,
    @SerializedName("humidity")
    val humidity: Float?,
    @SerializedName("is_raining")
    val rain: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("localization")
    val localization: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("lat")
    val lat: Float,
    @SerializedName("lon")
    val lon: Float

) {
    fun toPresentationModel(): WeatherPresentationModel {
        return WeatherPresentationModel(
            stationName = stationName,
            time = time,
            temperature = temperature,
            pressure = pressure,
            humidity = humidity,
            rain = rain,
            description = description,
            localization = localization,
            date = date
        )
    }
}