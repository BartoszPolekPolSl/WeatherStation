package com.example.weatherstation.data.rest

import com.example.weatherstation.data.dto.WeatherResponse
import retrofit2.http.GET

interface WeatherApi {
    @GET
    suspend fun getWeatherData(): WeatherResponse
}

const val BASE_URL = ""