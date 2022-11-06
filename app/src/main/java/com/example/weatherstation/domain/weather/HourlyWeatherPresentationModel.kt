package com.example.weatherstation.domain.weather

import androidx.annotation.DrawableRes

data class HourlyWeatherPresentationModel(
    val time: String,
    @DrawableRes val icon: Int,
    val temperature: String
)