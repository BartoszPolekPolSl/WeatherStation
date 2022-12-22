package com.example.weatherstation.presentation.ui.functionalities.weather

import android.app.Activity
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import com.example.weatherstation.R
import com.example.weatherstation.data.model.weather.HourlyWeatherPresentationModel
import com.example.weatherstation.presentation.LocalSharedPreferencesProvider
import com.example.weatherstation.presentation.ui.functionalities.settings.SettingsActivity
import com.example.weatherstation.presentation.ui.theme.WeatherStationTheme
import com.patrykandpatryk.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatryk.vico.core.entry.entriesOf
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class WeatherActivity : ComponentActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val viewModel: WeatherViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompositionLocalProvider(LocalSharedPreferencesProvider provides sharedPreferences) {
                WeatherStationTheme {
                    val chartStepEntryModelProducer = ChartEntryModelProducer()
                    WeatherScreen(
                        stations = viewModel.searchResult.collectAsState().value ?: listOf(),
                        currentStation = viewModel.currentStation,
                        onQueryChange = { viewModel.onSearchQueryChange(it) },
                        onClearIconClick = { viewModel.clearQuery() },
                        query = viewModel.query.value,
                        clearQuery = { viewModel.clearQuery() },
                        onStationClick = { viewModel.onStationClick(it) },
                        weatherResponse = viewModel.mainWeatherDataResponse.weather,
                        hourlyWeatherPresentationModels = hourlyWeatherPresentationModels,
                        historyState = viewModel.historyResponse,
                        onSettingsClick = { onSettingsClick() },
                        onRefresh = { viewModel.loadWeather() },
                        isRefreshing = viewModel.mainWeatherDataResponse.isLoading
                    )
                    chartStepEntryModelProducer.setEntries(
                        entriesOf(
                            1 to -5,
                            2 to 20,
                            3 to 15,
                            4 to -10,
                            5 to 20,
                            7 to 1,
                            8 to -5,
                            9 to 20,
                            10 to 15,
                            11 to -10,
                            12 to 20,
                            13 to 0.5,
                        )
                    )
                }
            }
        }
        viewModel.loadStations()
    }

    private fun onSettingsClick() {
        startForResult.launch(Intent(this, SettingsActivity::class.java))
    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                viewModel.loadWeather()
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

private val hourlyWeatherPresentationModels = listOf(
    HourlyWeatherPresentationModel("11", R.drawable.sun_icon, "22"),
    HourlyWeatherPresentationModel("12", R.drawable.clouds_icon, "22"),
    HourlyWeatherPresentationModel("13", R.drawable.sun_icon, "22"),
    HourlyWeatherPresentationModel("14", R.drawable.clouds_icon, "22"),
)