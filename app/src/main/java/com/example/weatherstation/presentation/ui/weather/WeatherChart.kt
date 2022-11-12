package com.example.weatherstation.presentation.ui.weather

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.weatherstation.presentation.ui.components.IntFormatAxisValueFormatter
import com.example.weatherstation.presentation.ui.components.marker
import com.patrykandpatryk.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatryk.vico.compose.axis.vertical.startAxis
import com.patrykandpatryk.vico.compose.chart.Chart
import com.patrykandpatryk.vico.compose.chart.column.columnChart
import com.patrykandpatryk.vico.core.entry.ChartEntryModelProducer

@Composable
fun WeatherChart(
    modifier: Modifier = Modifier,
    chartEntryModelProducer: ChartEntryModelProducer,
) {
    val startAxis = startAxis(
        maxLabelCount = 5,
        valueFormatter = IntFormatAxisValueFormatter(),
        axis = null
    )
    val columnChart = columnChart(
    )
    Chart(
        modifier = modifier,
        chart = columnChart,
        chartModelProducer = chartEntryModelProducer,
        startAxis = startAxis,
        bottomAxis = bottomAxis(
            tickLength = 0.dp,
            guideline = null
        ),
        marker = marker(),
    )
}
