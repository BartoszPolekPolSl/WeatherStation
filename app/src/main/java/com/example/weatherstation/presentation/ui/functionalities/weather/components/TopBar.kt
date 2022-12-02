package com.example.weatherstation.presentation.ui.functionalities.weather.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.weatherstation.R


@Composable
fun TopBar(onSettingsClick: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(top = 10.dp), horizontalArrangement = Arrangement.End
    ) {
        IconButton(onClick = onSettingsClick, modifier = Modifier.padding(end = 16.dp)) {
            Icon(painter = painterResource(R.drawable.settings_icon), contentDescription = null)
        }
    }
}