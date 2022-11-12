package com.example.weatherstation.presentation.ui.styles

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.weatherstation.R

val mainFontFamily =
    FontFamily(
        fonts = listOf(
            Font(R.font.satoshi_bold, weight = FontWeight.Bold),
            Font(R.font.satoshi_light, weight = FontWeight.Light),
            Font(R.font.satoshi_regular, weight = FontWeight.Normal),
            Font(R.font.satoshi_medium, weight = FontWeight.Medium),
            Font(R.font.satoshi_black, weight = FontWeight.Black)
        )
    )

val textBold
    @Composable
    @ReadOnlyComposable
    get() = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        fontSize = 24.sp,
        letterSpacing = 0.5.sp,
        lineHeight = 28.sp
    )

val textMedium
    @Composable
    @ReadOnlyComposable
    get() = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Medium,
        color = Color.White,
        fontSize = 18.sp,
        letterSpacing = 0.16.sp,
        lineHeight = 24.sp
    )

val textBlack
    @Composable
    @ReadOnlyComposable
    get() = TextStyle(
        fontFamily = mainFontFamily,
        fontWeight = FontWeight.Black,
        color = Color.White,
        fontSize = 9.sp,
        letterSpacing = 0.16.sp,
        lineHeight = 12.sp
    )