package com.example.weatherstation.data.repository

import com.example.weatherstation.data.model.weather.WeatherPresentationModel
import com.example.weatherstation.presentation.ui.util.Resource

interface WeatherRepository {
    suspend fun getWeatherData(
        stationId: Int
    ): Resource<WeatherPresentationModel>
}