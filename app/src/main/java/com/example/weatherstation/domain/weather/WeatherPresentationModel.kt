package com.example.weatherstation.domain.weather

data class WeatherPresentationModel(
    val time: String,
    val temperature: String,
    val pressure: String,
    val humidity: String,
    val rain: String
)