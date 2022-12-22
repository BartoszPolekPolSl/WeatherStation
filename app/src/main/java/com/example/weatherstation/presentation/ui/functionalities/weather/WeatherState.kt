package com.example.weatherstation.presentation.ui.functionalities.weather

import com.example.weatherstation.data.dto.WeatherResponse

data class WeatherState(
    val weather: WeatherResponse? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)