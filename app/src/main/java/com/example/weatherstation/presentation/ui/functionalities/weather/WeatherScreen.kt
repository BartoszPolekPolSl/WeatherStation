package com.example.weatherstation.presentation.ui.functionalities.weather

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.BottomSheetScaffold
import androidx.compose.material.BottomSheetScaffoldState
import androidx.compose.material.BottomSheetState
import androidx.compose.material.BottomSheetValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.material.rememberBottomSheetScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.weatherstation.data.dto.Station
import com.example.weatherstation.data.dto.WeatherResponse
import com.example.weatherstation.data.model.weather.HourlyWeatherPresentationModel
import com.example.weatherstation.presentation.ui.functionalities.weather.components.BottomSheetContent
import com.example.weatherstation.presentation.ui.functionalities.weather.components.TopBar
import com.example.weatherstation.presentation.ui.functionalities.weather.components.WeatherMainContent
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun WeatherScreen(
    stations: List<Station>,
    currentStation: Station?,
    onQueryChange: (String) -> Unit,
    query: String,
    clearQuery: () -> Unit,
    onClearIconClick: () -> Unit,
    onStationClick: (Station) -> Unit,
    weatherResponse: WeatherResponse?,
    hourlyWeatherPresentationModels: List<HourlyWeatherPresentationModel>,
    historyState: HistoryState,
    onSettingsClick: () -> Unit,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    style: WeatherScreenStyle = weatherScreenStyle()
) {
    val coroutineScope = rememberCoroutineScope()
    val pagerState = rememberPagerState()
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { onRefresh() })
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val focusManager = LocalFocusManager.current
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            BottomSheetContent(
                hourlyWeatherPresentationModels = hourlyWeatherPresentationModels,
                currentStation = currentStation,
                historyState = historyState,
                pagerState = pagerState,
                onPageChange = {
                    onPageChange(
                        coroutineScope = coroutineScope,
                        bottomSheetScaffoldState = bottomSheetScaffoldState,
                        pagerState = pagerState
                    )
                }
            )
        },
        sheetShape = style.bottomSheetShape,
        sheetPeekHeight = style.sheetPeekHeight,
        sheetBackgroundColor = style.sheetBackgroundColor
    ) {
        Box(
            Modifier
                .pullRefresh(pullRefreshState)
                .clickable {
                    clearQuery()
                    coroutineScope.launch {
                        bottomSheetScaffoldState.bottomSheetState.collapse()
                    }
                    focusManager.clearFocus()
                }) {
            Box {
                WeatherMainContent(weatherResponse)
                TopBar(
                    stations = stations,
                    onQueryChange = onQueryChange,
                    onClearIconClick = onClearIconClick,
                    query = query,
                    onStationClick = onStationClick,
                    onSettingsClick = onSettingsClick
                )
            }
            PullRefreshIndicator(
                isRefreshing,
                pullRefreshState,
                Modifier.align(Alignment.TopCenter)
            )
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
private fun onPageChange(
    coroutineScope: CoroutineScope,
    bottomSheetScaffoldState: BottomSheetScaffoldState,
    pagerState: PagerState,
) {
    if (pagerState.currentPage == 1) {
        coroutineScope.launch {
            bottomSheetScaffoldState.bottomSheetState.expand()
        }
    } else {
        coroutineScope.launch {
            bottomSheetScaffoldState.bottomSheetState.collapse()
        }
    }
}

data class WeatherScreenStyle(
    val bottomSheetShape: CornerBasedShape,
    val sheetBackgroundColor: Color,
    val sheetPeekHeight: Dp,
)

@Composable
fun weatherScreenStyle(
    bottomSheetShape: CornerBasedShape = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 20.dp
    ),
    sheetBackgroundColor: Color = Color.White,
    sheetPeekHeight: Dp = 150.dp,
) = WeatherScreenStyle(
    bottomSheetShape = bottomSheetShape,
    sheetBackgroundColor = sheetBackgroundColor,
    sheetPeekHeight = sheetPeekHeight
)
