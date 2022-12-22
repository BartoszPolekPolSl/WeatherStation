package com.example.weatherstation.presentation.ui.functionalities.weather.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherstation.R
import com.example.weatherstation.data.dto.Station
import com.example.weatherstation.data.model.weather.HourlyWeatherPresentationModel
import com.example.weatherstation.presentation.ui.functionalities.weather.HistoryState
import com.example.weatherstation.presentation.ui.functionalities.weather.WeatherChart
import com.example.weatherstation.presentation.ui.styles.textBold
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class)
@Composable
fun BottomSheetContent(
    hourlyWeatherPresentationModels: List<HourlyWeatherPresentationModel>,
    currentStation: Station?,
    onPageChange: () -> Unit,
    pagerState: PagerState,
    historyState: HistoryState,
    style: BottomSheetContentStyle = bottomSheetContentStyle()
) {
    val coroutineScope = rememberCoroutineScope()
    LaunchedEffect(pagerState.currentPage) {
        onPageChange()
    }
    HorizontalPager(
        count = 2,
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 10.dp),
        userScrollEnabled = false,
        modifier = Modifier.heightIn(max = 450.dp)
    ) { page ->
        Column() {
            if (page == 0) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                ) {
                    Row() {
                        Spacer(modifier = Modifier.weight(0.25f))
                        Text(
                            text = stringResource(R.string.weather_today),
                            style = style.headerTextStyle,
                            modifier = Modifier
                                .padding(vertical = style.headerVerticalPadding)
                        )
                        IconButton(
                            onClick = { coroutineScope.launch { pagerState.animateScrollToPage(1) } },
                            modifier = Modifier.padding(start = 60.dp)
                        ) {
                            Icon(
                                painter = painterResource(R.drawable.arrow_right),
                                contentDescription = null
                            )
                        }
                    }
                    BottomSheetWeatherSlider(hourlyWeatherPresentationModels)
                    WeatherChart(historyState = historyState)
                }
            } else if (page == 1) {
                MapPage(pagerState = pagerState, currentStation = currentStation)
            }
        }

    }
}

@Composable
fun BottomSheetWeatherSlider(hourlyWeatherPresentationModels: List<HourlyWeatherPresentationModel>) {
    LazyRow(
        Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp)
    ) {
        items(hourlyWeatherPresentationModels) { item ->
            HourlyWeather(item)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ColumnScope.MapPage(
    pagerState: PagerState,
    currentStation: Station?,
    style: BottomSheetContentStyle = bottomSheetContentStyle()
) {
    if (currentStation != null) {
        val stationLocalization = LatLng(
            currentStation.lat.toDouble(),
            currentStation.lon.toDouble()
        )
        val cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(
                stationLocalization, 10f
            )
        }
        LaunchedEffect(currentStation) {
            cameraPositionState.position = CameraPosition.fromLatLngZoom(
                stationLocalization, 10f
            )
        }
        val coroutineScope = rememberCoroutineScope()
        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)

        ) {
            Row() {
                IconButton(
                    onClick = { coroutineScope.launch { pagerState.animateScrollToPage(0) } },
                    Modifier.padding(end = 60.dp)
                ) {
                    Icon(
                        painter = painterResource(R.drawable.arrow_left),
                        contentDescription = null
                    )
                }
                Text(
                    text = stringResource(R.string.station_location),
                    style = style.headerTextStyle,
                    modifier = Modifier
                        .padding(vertical = style.headerVerticalPadding)
                )
            }
            GoogleMap(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                cameraPositionState = cameraPositionState
            ) {
                Marker(
                    state = MarkerState(position = stationLocalization),
                    title = currentStation.localization,
                    snippet = currentStation.name
                )
            }
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
    ),
    headerVerticalPadding: Dp = 10.dp,
) = BottomSheetContentStyle(
    headerTextStyle = headerTextStyle,
    headerVerticalPadding = headerVerticalPadding
)
