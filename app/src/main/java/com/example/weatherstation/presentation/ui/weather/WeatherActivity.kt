package com.example.weatherstation.presentation.ui.weather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherstation.presentation.ui.theme.WeatherStationTheme

class WeatherActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherStationTheme {
                WeatherScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    WeatherStationTheme {
        WeatherScreen()
    }
}