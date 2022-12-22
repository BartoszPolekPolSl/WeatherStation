package com.example.weatherstation.presentation.ui.functionalities.settings.components

import android.app.Activity
import android.content.SharedPreferences
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.weatherstation.R
import com.example.weatherstation.presentation.ui.theme.MainBackground

@Composable
fun SettingsMainContent(
    sharedPreferences: SharedPreferences,
    settingsItemRows: List<SettingsItemRow>,
    style: SettingsMainContentStyle = settingsMainContentStyle()
) {
    val activity = (LocalContext.current as? Activity)
    Column(
        Modifier
            .fillMaxSize()
            .background(style.backgroundColor)
    ) {
        Text(
            text = stringResource(R.string.settings),
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp)
        )
        settingsItemRows.forEach {
            SettingsItem(settingsItemRow = it)
        }
        Button(
            onClick = {
                onSaveClick(
                    settingsItemRows = settingsItemRows,
                    sharedPreferences = sharedPreferences
                )
                activity?.setResult(Activity.RESULT_OK)
                activity?.finish()
            },
            Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 30.dp)
        ) {
            Text(text = stringResource(R.string.save))
        }
    }
}

private fun onSaveClick(
    settingsItemRows: List<SettingsItemRow>,
    sharedPreferences: SharedPreferences
) {
    settingsItemRows.forEach {
        sharedPreferences.edit().putString(
            it.parameter.tag,
            it.currentUnit.symbol
        ).apply()
    }
}

data class SettingsMainContentStyle(val backgroundColor: Brush = MainBackground)

@Composable
fun settingsMainContentStyle(backgroundColor: Brush = MainBackground) =
    SettingsMainContentStyle(backgroundColor = backgroundColor)