package com.example.weatherstation.presentation.ui.functionalities.settings

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import com.example.weatherstation.presentation.ui.functionalities.settings.components.SettingsItemRow
import com.example.weatherstation.presentation.ui.functionalities.settings.components.SettingsMainContent

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SettingsScreen(settingsItemRows: List<SettingsItemRow>, sharedPreferences: SharedPreferences) {
    Scaffold {
        SettingsMainContent(
            sharedPreferences = sharedPreferences,
            settingsItemRows = settingsItemRows
        )
    }
}
