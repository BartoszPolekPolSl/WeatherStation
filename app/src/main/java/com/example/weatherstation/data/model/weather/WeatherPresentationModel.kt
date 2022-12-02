package com.example.weatherstation.data.model.weather

data class WeatherPresentationModel(
    val stationName: String,
    val time: String,
    val temperature: String?,
    val pressure: Float?,
    val humidity: Float?,
    val rain: String?,
    val description: String?,
    val localization: String,
    val date: String,
)