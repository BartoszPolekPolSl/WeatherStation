package com.example.weatherstation.presentation.ui.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.weatherstation.R
import com.example.weatherstation.domain.weather.HourlyWeatherPresentationModel
import com.example.weatherstation.domain.weather.WeatherPresentationModel
import com.example.weatherstation.presentation.ui.theme.WeatherStationTheme
import com.patrykandpatryk.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatryk.vico.core.entry.entriesOf

class WeatherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherStationTheme {
                val chartStepEntryModelProducer = ChartEntryModelProducer()
                WeatherScreen(
                    presentationModel,
                    hourlyWeatherPresentationModels,
                    chartStepEntryModelProducer
                )
                chartStepEntryModelProducer.setEntries(
                    entriesOf(
                        1 to 1000,
                        2 to 1020,
                        3 to 950,
                        4 to 980,
                        5 to 1000,
                        6 to 1000,
                        7 to 1010
                    )
                )
            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    WeatherStationTheme {
        WeatherScreen(presentationModel, hourlyWeatherPresentationModels)
    }
}*/

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


private val hourlyWeatherPresentationModels = listOf(
    HourlyWeatherPresentationModel("11", R.drawable.sun_icon, "22"),
    HourlyWeatherPresentationModel("12", R.drawable.clouds_icon, "22"),
    HourlyWeatherPresentationModel("13", R.drawable.sun_icon, "22"),
    HourlyWeatherPresentationModel("14", R.drawable.clouds_icon, "22"),
)