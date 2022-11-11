package com.example.weatherstation.presentation.ui.weather

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherstation.R
import com.example.weatherstation.domain.weather.HourlyWeatherPresentationModel
import com.example.weatherstation.presentation.ui.styles.textBold

@Composable
fun HourlyWeather(
    presentationModel: HourlyWeatherPresentationModel,
    style: HourlyWeatherStyle = hourlyWeatherStyle()
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 30.dp)
    ) {
        Text(
            text = presentationModel.time,
            style = style.timeTextStyle,
            modifier = Modifier.padding(bottom = style.timeBottomPadding)
        )
        Image(painter = painterResource(presentationModel.icon), contentDescription = null)
        Row {
            Text(
                text = presentationModel.temperature,
                style = style.temperatureTextStyle
            )
            Text(text = stringResource(R.string.celsius), style = style.temperatureTextStyle)
        }
    }
}

data class HourlyWeatherStyle(
    val timeBottomPadding: Dp,
    val timeTextStyle: TextStyle,
    val temperatureTextStyle: TextStyle
)

@Composable
fun hourlyWeatherStyle(
    timeBottomPadding: Dp = 12.dp,
    timeTextStyle: TextStyle = textBold.copy(
        color = Color.Black,
        fontSize = 14.sp,
        lineHeight = 14.sp
    ),
    temperatureTextStyle: TextStyle = textBold.copy(
        color = Color.Black,
        fontSize = 20.sp,
        lineHeight = 54.sp
    )
) = HourlyWeatherStyle(
    timeBottomPadding = timeBottomPadding,
    timeTextStyle = timeTextStyle,
    temperatureTextStyle = temperatureTextStyle
)

private val presentationModel =
    HourlyWeatherPresentationModel(time = "11", icon = R.drawable.clouds_icon, temperature = "1")

@Preview(showBackground = true, backgroundColor = 0x000000)
@Composable
fun HourlyWeatherPreview() {
    HourlyWeather(presentationModel)
}