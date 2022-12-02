package com.example.weatherstation.data.model.weather

import androidx.annotation.DrawableRes

data class HourlyWeatherPresentationModel(
    val time: String,
    @DrawableRes val icon: Int,
    val temperature: String
)