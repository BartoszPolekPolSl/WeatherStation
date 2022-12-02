package com.example.weatherstation.presentation.ui.functionalities.weather

import com.example.weatherstation.data.model.weather.WeatherPresentationModel

data class WeatherState(
    val weather: WeatherPresentationModel? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)