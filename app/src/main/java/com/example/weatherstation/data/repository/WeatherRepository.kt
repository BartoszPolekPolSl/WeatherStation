package com.example.weatherstation.data.repository

import com.example.weatherstation.domain.weather.WeatherPresentationModel
import com.example.weatherstation.presentation.ui.util.Resource

interface WeatherRepository {
    suspend fun getWeatherData(): Resource<WeatherPresentationModel> {
        TODO("Not yet implemented")
    }
}