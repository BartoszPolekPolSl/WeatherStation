package com.example.weatherstation.presentation.ui.functionalities.weather

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherstation.data.dto.DailyWeatherResponse
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
    var mainWeatherDataResponse by mutableStateOf(WeatherState())
        private set

    var stationsResponse by mutableStateOf(StationsState())
        private set

    var currentStation: Station? = null
        private set

    var historyResponse by mutableStateOf(HistoryState())
        private set

    private var _searchResult: MutableStateFlow<List<Station>?> =
        MutableStateFlow(stationsResponse.stations)
    var searchResult = _searchResult.asStateFlow()

    val query = mutableStateOf("")

    fun loadWeather() {
        viewModelScope.launch {
            mainWeatherDataResponse = mainWeatherDataResponse.copy(
                isLoading = true,
                error = null
            )
            when (val result = repository.getWeatherData(currentStation!!.id)) {
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
                    currentStation = stationsResponse.stations?.get(0)
                    query.value = currentStation!!.name
                    loadWeather()
                    loadWeatherChart()
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

    fun loadWeatherChart() {
        viewModelScope.launch {
            stationsResponse = stationsResponse.copy(
                isLoading = true,
                error = null
            )
            when (val result = repository.getHistory(currentStation!!.id, 7)) {
                is Resource.Success -> {
                    transformHistoryResponse(result = result)
                }

                is Resource.Error -> {
                    historyResponse = historyResponse.copy(
                        isLoading = false,
                        error = result.message
                    )
                }
            }
        }
    }

    private fun transformHistoryResponse(result: Resource<List<DailyWeatherResponse>>) {
        historyResponse = historyResponse.copy(
            isLoading = false,
            error = null,
            temperature = result.data!!.map { it.temperature },
            humidity = result.data.map { it.humidity },
            pressure = result.data.map { it.pressure },
            isRaining = result.data.map { it.isRaining }
        )
    }

    fun onStationClick(station: Station) {
        currentStation = station
        _searchResult.value = listOf()
        query.value = currentStation!!.name
        loadWeather()
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

    fun clearQuery() {
        query.value = currentStation!!.name
        _searchResult.value = null
    }
}
