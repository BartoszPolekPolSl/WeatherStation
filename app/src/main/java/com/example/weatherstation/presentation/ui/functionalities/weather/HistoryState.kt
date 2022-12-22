package com.example.weatherstation.presentation.ui.functionalities.weather

data class HistoryState(
    val isLoading: Boolean = false,
    val error: String? = null,
    val temperature: List<Float?>? = null,
    val pressure: List<Float?>? = null,
    val isRaining: List<Int?>? = null,
    val humidity: List<Float?>? = null
)