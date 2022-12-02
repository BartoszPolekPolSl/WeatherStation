package com.example.weatherstation.presentation.ui.functionalities.settings

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import com.example.weatherstation.data.model.settings.Parameters
import com.example.weatherstation.data.model.settings.Settings
import com.example.weatherstation.data.util.Units
import com.example.weatherstation.presentation.ui.functionalities.settings.components.SettingsItemRow
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    sharedPreferences: SharedPreferences,
    val settings: Settings
) :
    ViewModel() {
    val settingsItemRows: List<SettingsItemRow> =
        listOf(
            SettingsItemRow(
                parameter = Parameters.TEMPERATURE,
                currentUnit = Units.findBySymbol(
                    sharedPreferences.getString(
                        Parameters.TEMPERATURE.tag,
                        Parameters.TEMPERATURE.defaultUnit.symbol
                    )!!
                )!!
            ),
            SettingsItemRow(
                parameter = Parameters.PRESSURE,
                currentUnit = Units.findBySymbol(
                    sharedPreferences.getString(
                        Parameters.PRESSURE.tag,
                        Parameters.PRESSURE.defaultUnit.symbol
                    )!!
                )!!
            )
        )

}