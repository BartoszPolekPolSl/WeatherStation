package com.example.weatherstation.data.rest

import com.example.weatherstation.data.dto.Station
import com.example.weatherstation.data.dto.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("recent")
    suspend fun getWeatherData(
        @Query("station") stationId: Int,
        @Query("t_unit") temperatureUnit: String,
        @Query("p_unit") pressureUnit: String
    ): WeatherResponse

    @GET("stations")
    suspend fun getStations(): List<Station>
}

const val BASE_URL = "https://wthstation.bieda.it/api/v1/"