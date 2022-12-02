package com.example.weatherstation.presentation.ui.functionalities.weather.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherstation.R
import com.example.weatherstation.data.model.weather.HourlyWeatherPresentationModel
import com.example.weatherstation.presentation.ui.functionalities.weather.WeatherChart
import com.example.weatherstation.presentation.ui.styles.textBold
import com.patrykandpatryk.vico.core.entry.ChartEntryModelProducer

@Composable
fun BottomSheetContent(
    hourlyWeatherPresentationModels: List<HourlyWeatherPresentationModel>,
    chartStepEntryModerProducer: ChartEntryModelProducer,
    style: BottomSheetContentStyle = bottomSheetContentStyle()
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        Text(
            text = stringResource(R.string.weather_today),
            style = style.headerTextStyle,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(vertical = style.headerVerticalPadding)
        )
        BottomSheetWeatherSlider(hourlyWeatherPresentationModels)
        WeatherChart(chartEntryModelProducer = chartStepEntryModerProducer)
    }
}

@Composable
fun BottomSheetWeatherSlider(hourlyWeatherPresentationModels: List<HourlyWeatherPresentationModel>) {
    LazyRow(Modifier.fillMaxWidth()) {
        items(hourlyWeatherPresentationModels) { item ->
            HourlyWeather(item)
        }
    }
}

data class BottomSheetContentStyle(
    val headerTextStyle: TextStyle,
    val headerVerticalPadding: Dp
)

@Composable
fun bottomSheetContentStyle(
    headerTextStyle: TextStyle = textBold.copy(
        fontSize = 18.sp,
        color = Color.Gray
    ), headerVerticalPadding: Dp = 10.dp
) = BottomSheetContentStyle(
    headerTextStyle = headerTextStyle,
    headerVerticalPadding = headerVerticalPadding
)
