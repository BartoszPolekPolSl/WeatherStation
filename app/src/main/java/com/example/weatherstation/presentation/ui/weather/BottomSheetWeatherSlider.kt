package com.example.weatherstation.presentation.ui.weather

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.weatherstation.domain.weather.HourlyWeatherPresentationModel


@Composable
fun BottomSheetWeatherSlider(hourlyWeatherPresentationModels: List<HourlyWeatherPresentationModel>) {
    LazyRow(Modifier.fillMaxWidth()) {
        items(hourlyWeatherPresentationModels) { item ->
            HourlyWeather(item)
        }
    }
}