package com.example.weatherstation.presentation.ui.functionalities.settings

import android.content.SharedPreferences
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import com.example.weatherstation.presentation.ui.theme.WeatherStationTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SettingsActivity : ComponentActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherStationTheme {
                SettingsScreen(
                    settingsItemRows = viewModel.settingsItemRows,
                    sharedPreferences = sharedPreferences,
                )
            }
        }
    }
}