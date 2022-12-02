package com.example.weatherstation.data.repository

import android.content.SharedPreferences
import com.example.weatherstation.data.model.settings.Parameters
import com.example.weatherstation.data.model.weather.WeatherPresentationModel
import com.example.weatherstation.data.rest.WeatherApi
import com.example.weatherstation.data.util.Units
import com.example.weatherstation.presentation.ui.util.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val api: WeatherApi
) : WeatherRepository {

    override suspend fun getWeatherData(
        stationId: Int
    ): Resource<WeatherPresentationModel> {
        return try {
            Resource.Success(
                api.getWeatherData(
                    stationId = stationId,
                    temperatureUnit = Units.findBySymbol(
                        sharedPreferences.getString(
                            Parameters.TEMPERATURE.tag,
                            Units.CELSIUS.symbol
                        )!!
                    )!!.requestParameter,
                    pressureUnit = Units.findBySymbol(
                        sharedPreferences.getString(
                            Parameters.PRESSURE.tag,
                            Units.CELSIUS.symbol
                        )!!
                    )!!.requestParameter
                ).toPresentationModel()
            )
        } catch (e: IOException) {
            Resource.Error("You might not have intent connection")
        } catch (e: HttpException) {
            Resource.Error("Unexpected response")
        }
    }
}