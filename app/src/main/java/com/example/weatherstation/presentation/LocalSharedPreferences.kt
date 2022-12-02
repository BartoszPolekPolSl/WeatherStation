package com.example.weatherstation.presentation

import android.content.SharedPreferences
import androidx.compose.runtime.staticCompositionLocalOf

val LocalSharedPreferencesProvider = staticCompositionLocalOf<SharedPreferences> {
    error("SharedPreferences not present")
}