package com.example.weatherstation.presentation.ui.functionalities.weather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.weatherstation.data.dto.Station
import com.example.weatherstation.data.model.weather.HourlyWeatherPresentationModel
import com.example.weatherstation.data.model.weather.WeatherPresentationModel
import com.example.weatherstation.presentation.ui.functionalities.weather.components.BottomSheetContent
import com.example.weatherstation.presentation.ui.functionalities.weather.components.TopBar
import com.example.weatherstation.presentation.ui.functionalities.weather.components.WeatherMainContent
import com.patrykandpatryk.vico.core.entry.ChartEntryModelProducer


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeatherScreen(
    stations: List<Station>,
    onQueryChange: (String) -> Unit,
    query: String,
    onStationClick: (Int) -> Unit,
    presentationModel: WeatherPresentationModel?,
    hourlyWeatherPresentationModels: List<HourlyWeatherPresentationModel>,
    chartStepEntryModerProducer: ChartEntryModelProducer,
    onSettingsClick: () -> Unit,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    style: WeatherScreenStyle = weatherScreenStyle()
) {
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { onRefresh() })
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
        Box(Modifier.pullRefresh(pullRefreshState)) {
            Box {
                WeatherMainContent(
                    stations = stations,
                    onQueryChange = onQueryChange,
                    query = query,
                    onStationClick = onStationClick,
                    weatherPresentationModel = presentationModel,
                    onSettingsClick = onSettingsClick,
                    onRefresh = onRefresh,
                    isRefreshing = isRefreshing
                )
                TopBar(
                    stations = stations,
                    onQueryChange = onQueryChange,
                    query = query,
                    onStationClick = onStationClick
                ) { onSettingsClick() }
            }
            PullRefreshIndicator(
                isRefreshing,
                pullRefreshState,
                Modifier.align(Alignment.TopCenter)
            )
        }
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
