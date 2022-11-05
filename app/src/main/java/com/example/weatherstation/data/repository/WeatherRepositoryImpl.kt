package com.example.weatherstation.data.repository

import com.example.weatherstation.data.rest.WeatherApi
import com.example.weatherstation.domain.weather.WeatherPresentationModel
import com.example.weatherstation.presentation.ui.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api: WeatherApi) : WeatherRepository {

    override suspend fun getWeatherData(): Resource<WeatherPresentationModel> {
        return try {
            Resource.Success(api.getWeatherData().toPresentationModel())
        } catch (e: IOException) {
            Resource.Error("You might not have intent connection")
        } catch (e: HttpException) {
            Resource.Error("Unexpected response")
        }
    }
}