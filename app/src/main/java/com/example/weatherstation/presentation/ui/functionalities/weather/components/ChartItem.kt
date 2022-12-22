package com.example.weatherstation.presentation.ui.functionalities.weather.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherstation.presentation.ui.components.IntFormatAxisValueFormatter
import com.example.weatherstation.presentation.ui.components.marker
import com.patrykandpatryk.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatryk.vico.compose.axis.vertical.startAxis
import com.patrykandpatryk.vico.compose.chart.Chart
import com.patrykandpatryk.vico.compose.chart.line.lineChart
import com.patrykandpatryk.vico.core.entry.ChartEntryModelProducer
import com.patrykandpatryk.vico.core.entry.entriesOf

@Composable
fun ChartContainer(chartModel: MutableList<Pair<Float, Float>>, header: String) {
    val startAxis = startAxis(
        maxLabelCount = 5,
        valueFormatter = IntFormatAxisValueFormatter(),
        axis = null
    )
    Column(Modifier.padding(bottom = 10.dp)) {
        Text("Temperature []")
        Chart(
            chart = lineChart(),
            chartModelProducer = ChartEntryModelProducer(entriesOf(*chartModel.toTypedArray())),
            startAxis = startAxis,
            bottomAxis = bottomAxis(
                tickLength = 0.dp,
                guideline = null
            ),
            marker = marker(),
        )
    }
}