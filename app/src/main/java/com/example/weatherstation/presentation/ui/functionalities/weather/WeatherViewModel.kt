package com.example.weatherstation.presentation.ui.functionalities.weather

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherstation.data.model.settings.Settings
import com.example.weatherstation.data.repository.WeatherRepository
import com.example.weatherstation.presentation.ui.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    val settings: Settings,
) : ViewModel() {

    var mainWeatherDataResponse by mutableStateOf(WeatherState())
        private set

    fun loadWeather() {
        viewModelScope.launch {
            mainWeatherDataResponse = mainWeatherDataResponse.copy(
                isLoading = true,
                error = null
            )
            when (val result = repository.getWeatherData(1)) {
                is Resource.Success -> {
                    mainWeatherDataResponse = mainWeatherDataResponse.copy(
                        weather = result.data,
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    mainWeatherDataResponse = mainWeatherDataResponse.copy(
                        weather = null,
                        isLoading = false,
                        error = result.message
                    )
                }

            }
        }
    }
}