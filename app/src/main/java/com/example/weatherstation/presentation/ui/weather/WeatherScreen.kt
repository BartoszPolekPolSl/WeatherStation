package com.example.weatherstation.presentation.ui.weather

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.weatherstation.domain.weather.WeatherPresentationModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeatherScreen(style: WeatherScreenStyle = weatherScreenStyle()) {
    BottomSheetScaffold(
        sheetContent = { BottomSheetContent() },
        sheetShape = style.bottomSheetShape,
        sheetPeekHeight = style.sheetPeekHeight
    ) {
        MainContent(presentationModel = presentationModel)
    }
}

data class WeatherScreenStyle(val bottomSheetShape: CornerBasedShape, val sheetPeekHeight: Dp)

@Composable
fun weatherScreenStyle(
    bottomSheetShape: CornerBasedShape = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 20.dp
    ),
    sheetPeekHeight: Dp = 150.dp
) = WeatherScreenStyle(bottomSheetShape = bottomSheetShape, sheetPeekHeight = sheetPeekHeight)

private val presentationModel = WeatherPresentationModel(
    time = "00:00",
    temperature = "1",
    pressure = "1000",
    humidity = "80",
    rain = "yes",
    description = "little windy today, dress warmly",
    city = "Katowice",
    date = "tue. 01.11"
)