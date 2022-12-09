package com.example.weatherstation.presentation.ui.functionalities.weather

import com.example.weatherstation.data.dto.Station

data class StationsState(
    val stations: List<Station>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)