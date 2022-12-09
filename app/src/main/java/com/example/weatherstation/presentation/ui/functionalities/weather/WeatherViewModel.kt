package com.example.weatherstation.presentation.ui.functionalities.weather

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherstation.data.dto.Station
import com.example.weatherstation.data.model.settings.Settings
import com.example.weatherstation.data.repository.WeatherRepository
import com.example.weatherstation.presentation.ui.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    val settings: Settings,
) : ViewModel() {

    var currentStation: Int = 1
        private set

    var mainWeatherDataResponse by mutableStateOf(WeatherState())
        private set

    var stationsResponse by mutableStateOf(StationsState())
        private set

    private var _searchResult: MutableStateFlow<List<Station>?> =
        MutableStateFlow(stationsResponse.stations)
    var searchResult = _searchResult.asStateFlow()

    val query = mutableStateOf("")

    fun loadWeather(stationId: Int) {
        viewModelScope.launch {
            mainWeatherDataResponse = mainWeatherDataResponse.copy(
                isLoading = true,
                error = null
            )
            when (val result = repository.getWeatherData(stationId)) {
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

    fun loadStations() {
        viewModelScope.launch {
            stationsResponse = stationsResponse.copy(
                isLoading = true,
                error = null
            )
            when (val result = repository.getStations()) {
                is Resource.Success -> {
                    stationsResponse = stationsResponse.copy(
                        stations = result.data,
                        isLoading = false,
                        error = null
                    )
                }

                is Resource.Error -> {
                    stationsResponse = stationsResponse.copy(
                        stations = null,
                        isLoading = false,
                        error = result.message
                    )
                }

            }
        }
    }

    fun onStationClick(stationId: Int) {
        currentStation = stationId
        loadWeather(currentStation)
    }

    fun onSearchQueryChange(query: String) {
        this.query.value = query
        if (query.isEmpty()) {
            _searchResult.value = null
        } else {
            val result = stationsResponse.stations?.filter { station ->
                station.name.contains(query) || station.localization.contains(query)
            }
            _searchResult.value = result
        }
    }
}
