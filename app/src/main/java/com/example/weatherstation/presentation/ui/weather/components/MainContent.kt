package com.example.weatherstation.presentation.ui.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherstation.R
import com.example.weatherstation.domain.weather.WeatherPresentationModel
import com.example.weatherstation.presentation.ui.components.CircularIndicator
import com.example.weatherstation.presentation.ui.styles.textBold
import com.example.weatherstation.presentation.ui.styles.textMedium
import com.example.weatherstation.presentation.ui.theme.MainBackground

@Composable
fun MainContent(
    presentationModel: WeatherPresentationModel,
    style: MainContentStyle = mainContentStyle()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(style.backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(R.drawable.sun),
            contentDescription = null,
            modifier = Modifier.padding(top = style.weatherImageTopPadding)
        )
        Column(
            Modifier.offset(y = style.informationSectionOffset),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            InformationSection(presentationModel = presentationModel, style = style)
            IndicatorsSection()
        }
    }
}

@Composable
fun InformationSection(presentationModel: WeatherPresentationModel, style: MainContentStyle) {
    Row {
        Text(
            text = presentationModel.temperature,
            style = style.temperatureTextStyle
        )
        Text(
            text = stringResource(R.string.celsius),
            style = style.temperatureTextStyle
        )
    }
    Text(text = presentationModel.city, style = style.cityTextStyle)
    Text(text = presentationModel.date, style = style.dateTextStyle)
    Text(
        text = presentationModel.description,
        style = style.descriptionTextStyle,
        modifier = Modifier.padding(top = style.descriptionTopPadding)
    )
}

@Composable
fun IndicatorsSection() {
    Row() {
        CircularIndicator(
            minIndicator = "950",
            maxIndicator = "1050",
            icon = R.drawable.sun_icon,
            indicatorValue = 1000,
            maxIndicatorValue = 1050,
            subText = "hPa"
        )
        CircularIndicator(
            minIndicator = "0",
            maxIndicator = "100",
            indicatorValue = 80,
            subText = "%",
            maxIndicatorValue = 1050
        )
    }
}

data class MainContentStyle(
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
fun mainContentStyle(
    backgroundColor: Brush = MainBackground,
    weatherImageTopPadding: Dp = 20.dp,
    informationSectionOffset: Dp = (-70).dp,
    temperatureTextStyle: TextStyle = textBold.copy(
        fontSize = 88.sp,
        lineHeight = 103.sp
    ),
    cityTextStyle: TextStyle = textBold,
    dateTextStyle: TextStyle = textBold,
    descriptionTextStyle: TextStyle = textMedium,
    descriptionTopPadding: Dp = 21.dp
) = MainContentStyle(
    backgroundColor = backgroundColor,
    weatherImageTopPadding = weatherImageTopPadding,
    informationSectionOffset = informationSectionOffset,
    temperatureTextStyle = temperatureTextStyle,
    cityTextStyle = cityTextStyle,
    dateTextStyle = dateTextStyle,
    descriptionTextStyle = descriptionTextStyle,
    descriptionTopPadding = descriptionTopPadding
)