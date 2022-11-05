package com.example.weatherstation.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.weatherstation.presentation.ui.theme.MainBackground
import com.example.weatherstation.presentation.ui.theme.WeatherStationTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterialApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherStationTheme {

            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Screen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MainBackground)
    ) {
    }
}

@Composable
private fun ScaffoldContent() {
}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    WeatherStationTheme {
        Screen()
    }
}