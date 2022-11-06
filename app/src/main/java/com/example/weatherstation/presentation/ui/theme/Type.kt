package com.example.weatherstation.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weatherstation.R

val fonts = FontFamily(
    Font(R.font.satoshi_black),
    Font(R.font.satoshi_bold, weight = FontWeight.Bold),
    Font(R.font.satoshi_light, weight = FontWeight.Light),
    Font(R.font.satoshi_regular, weight = FontWeight.Normal),
    Font(R.font.satoshi_medium, weight = FontWeight.Medium)
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
)

