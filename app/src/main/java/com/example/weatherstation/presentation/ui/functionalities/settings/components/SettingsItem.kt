package com.example.weatherstation.presentation.ui.functionalities.settings.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.weatherstation.data.model.settings.Parameters
import com.example.weatherstation.data.util.Units

@Composable
fun SettingsItem(settingsItemRow: SettingsItemRow) {
    var currentUnitState by remember {
        mutableStateOf(
            settingsItemRow.currentUnit
        )
    }
    Column(Modifier.padding(start = 16.dp)) {
        Text(text = stringResource(settingsItemRow.parameter.title))
        Row(Modifier.padding(top = 10.dp)) {
            settingsItemRow.parameter.units.forEach { unit ->
                Row(Modifier.padding(end = 5.dp), verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = currentUnitState == unit,
                        onCheckedChange = {
                            currentUnitState = unit
                            settingsItemRow.currentUnit = unit
                        })
                    Text(unit.symbol)
                }
            }
        }
    }
}

data class SettingsItemRow(
    val parameter: Parameters,
    var currentUnit: Units
)