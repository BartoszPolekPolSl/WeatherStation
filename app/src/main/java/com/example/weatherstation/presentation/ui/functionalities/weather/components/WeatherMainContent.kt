package com.example.weatherstation.presentation.ui.functionalities.weather.components

import android.content.SharedPreferences
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherstation.R
import com.example.weatherstation.data.model.settings.Parameters
import com.example.weatherstation.data.model.weather.WeatherPresentationModel
import com.example.weatherstation.data.util.Units
import com.example.weatherstation.presentation.LocalSharedPreferencesProvider
import com.example.weatherstation.presentation.ui.components.CircularIndicator
import com.example.weatherstation.presentation.ui.styles.textBold
import com.example.weatherstation.presentation.ui.styles.textMedium
import com.example.weatherstation.presentation.ui.theme.MainBackground

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun WeatherMainContent(
    presentationModel: WeatherPresentationModel?,
    onRefresh: () -> Unit,
    isRefreshing: Boolean,
    onSettingsClick: () -> Unit,
    style: WeatherMainContentStyle = weatherMainContentStyle()
) {
    val pullRefreshState = rememberPullRefreshState(isRefreshing, { onRefresh() })
    Box(Modifier.pullRefresh(pullRefreshState)) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(style.backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                TopBar { onSettingsClick() }
                Image(
                    painter = painterResource(R.drawable.sun),
                    contentDescription = null,
                    modifier = Modifier.padding(top = style.weatherImageTopPadding)
                )
                presentationModel?.let {
                    Column(
                        Modifier.offset(y = style.informationSectionOffset),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        InformationSection(presentationModel = presentationModel, style = style)
                        IndicatorsSection(
                            Pair(
                                readUnit(
                                    Parameters.PRESSURE,
                                    LocalSharedPreferencesProvider.current
                                ), presentationModel.pressure
                            ),
                            Pair(Units.PERCENT, presentationModel.humidity)
                        )
                    }
                }
            }
        }
        PullRefreshIndicator(
            isRefreshing,
            pullRefreshState,
            Modifier.align(Alignment.TopCenter)
        )
    }
}

private fun readUnit(parameters: Parameters, sharedPreferences: SharedPreferences): Units {
    return Units.findBySymbol(
        sharedPreferences.getString(
            parameters.tag,
            Units.CELSIUS.symbol
        )!!
    )!!
}

@Composable
fun InformationSection(
    presentationModel: WeatherPresentationModel,
    style: WeatherMainContentStyle
) {
    Row {
        Text(
            text = presentationModel.temperature ?: "N/A",
            style = style.temperatureTextStyle
        )
    }
    Text(text = presentationModel.localization, style = style.cityTextStyle)
    Text(text = presentationModel.date, style = style.dateTextStyle)
    if (presentationModel.description != null) {
        Text(
            text = presentationModel.description ?: "",
            style = style.descriptionTextStyle,
            modifier = Modifier.padding(top = style.descriptionTopPadding)
        )
    }
}

@Composable
fun IndicatorsSection(pressureValue: Pair<Units, Float?>, humidityValue: Pair<Units, Float?>) {
    Row {
        if (pressureValue.second != null) {
            CircularIndicator(
                unit = pressureValue.first,
                icon = R.drawable.sun_icon,
                indicatorValue = pressureValue.second!!,
                subText = pressureValue.first.symbol
            )
        } else {
            CircularIndicator(
                unit = pressureValue.first,
                icon = R.drawable.sun_icon,
                indicatorValue = 0f,
                subText = pressureValue.first.symbol
            )
        }
        if (humidityValue.second != null) {
            CircularIndicator(
                unit = humidityValue.first,
                indicatorValue = humidityValue.second!!,
                subText = pressureValue.first.symbol,
            )
        } else {
            CircularIndicator(
                unit = humidityValue.first,
                indicatorValue = 0f,
                subText = pressureValue.first.symbol,
            )
        }


    }
}

data class WeatherMainContentStyle(
    val backgroundColor: Brush,
    val weatherImageTopPadding: Dp,
    val informationSectionOffset: Dp,
    val temperatureTextStyle: TextStyle,
    val cityTextStyle: TextStyle,
    val dateTextStyle: TextStyle,
    val descriptionTextStyle: TextStyle,
    val descriptionTopPadding: Dp
)

@Composable
fun weatherMainContentStyle(
    backgroundColor: Brush = MainBackground,
    weatherImageTopPadding: Dp = 10.dp,
    informationSectionOffset: Dp = (-70).dp,
    temperatureTextStyle: TextStyle = textBold.copy(
        fontSize = 88.sp,
        lineHeight = 103.sp
    ),
    cityTextStyle: TextStyle = textBold,
    dateTextStyle: TextStyle = textBold,
    descriptionTextStyle: TextStyle = textMedium,
    descriptionTopPadding: Dp = 21.dp
) = WeatherMainContentStyle(
    backgroundColor = backgroundColor,
    weatherImageTopPadding = weatherImageTopPadding,
    informationSectionOffset = informationSectionOffset,
    temperatureTextStyle = temperatureTextStyle,
    cityTextStyle = cityTextStyle,
    dateTextStyle = dateTextStyle,
    descriptionTextStyle = descriptionTextStyle,
    descriptionTopPadding = descriptionTopPadding
)