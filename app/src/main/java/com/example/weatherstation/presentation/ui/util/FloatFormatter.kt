package com.example.weatherstation.presentation.ui.util

fun Float.getFormattedString(): String {
    return if ((this * 1000f) % 1000f == 0f) {
        this.toInt().toString()
    } else {
        this.toString()
    }
}