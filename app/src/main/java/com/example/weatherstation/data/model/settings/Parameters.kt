package com.example.weatherstation.data.model.settings

import androidx.annotation.StringRes
import com.example.weatherstation.R
import com.example.weatherstation.data.util.Units

enum class Parameters(
    @StringRes val title: Int,
    val tag: String,
    val units: List<Units>,
    val defaultUnit: Units
) {
    TEMPERATURE(
        R.string.temperature,
        "TEMP",
        listOf(Units.CELSIUS, Units.FAHRENHEIT),
        Units.CELSIUS
    ),
    PRESSURE(
        R.string.pressure,
        "PRE",
        listOf(Units.HECTOPASCAL, Units.BAR, Units.PSI),
        Units.HECTOPASCAL
    )
}