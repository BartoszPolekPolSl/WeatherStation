package com.example.weatherstation.data.repository

import com.example.weatherstation.data.dto.DailyWeatherResponse
import com.example.weatherstation.data.dto.Station
import com.example.weatherstation.data.dto.WeatherResponse
import com.example.weatherstation.presentation.ui.util.Resource

interface WeatherRepository {
    suspend fun getWeatherData(
        stationId: Int
    ): Resource<WeatherResponse>

    suspend fun getStations(): Resource<List<Station>>

    suspend fun getHistory(stationId: Int, n: Int): Resource<List<DailyWeatherResponse>>
}