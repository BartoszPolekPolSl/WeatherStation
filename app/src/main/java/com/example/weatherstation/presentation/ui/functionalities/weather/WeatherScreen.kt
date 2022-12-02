package com.example.weatherstation.presentation.ui.functionalities.weather

import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.weatherstation.data.model.weather.HourlyWeatherPresentationModel
import com.example.weatherstation.data.model.weather.WeatherPresentationModel
import com.example.weatherstation.presentation.ui.functionalities.weather.components.BottomSheetContent
import com.example.weatherstation.presentation.ui.functionalities.weather.components.WeatherMainContent
import com.patrykandpatryk.vico.core.entry.ChartEntryModelProducer


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeatherScreen(
    presentationModel: WeatherPresentationModel?,
    hourlyWeatherPresentationModels: List<HourlyWeatherPresentationModel>,
    chartStepEntryModerProducer: ChartEntryModelProducer,
    onSettingsClick: () -> Unit,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    style: WeatherScreenStyle = weatherScreenStyle()
) {
    BottomSheetScaffold(
        sheetContent = {
            BottomSheetContent(
                hourlyWeatherPresentationModels,
                chartStepEntryModerProducer
            )
        },
        sheetShape = style.bottomSheetShape,
        sheetPeekHeight = style.sheetPeekHeight,
        sheetBackgroundColor = style.sheetBackgroundColor
    ) {
        WeatherMainContent(
            presentationModel = presentationModel,
            onSettingsClick = onSettingsClick,
            onRefresh = onRefresh,
            isRefreshing = isRefreshing
        )
    }
}


data class WeatherScreenStyle(
    val bottomSheetShape: CornerBasedShape,
    val sheetPeekHeight: Dp,
    val sheetBackgroundColor: Color
)

@Composable
fun weatherScreenStyle(
    bottomSheetShape: CornerBasedShape = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 20.dp
    ),
    sheetPeekHeight: Dp = 150.dp,
    sheetBackgroundColor: Color = Color.White
) = WeatherScreenStyle(
    bottomSheetShape = bottomSheetShape,
    sheetPeekHeight = sheetPeekHeight,
    sheetBackgroundColor = sheetBackgroundColor
)
