package com.example.weatherstation.presentation.ui.functionalities.weather

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.weatherstation.presentation.ui.components.IntFormatAxisValueFormatter
import com.example.weatherstation.presentation.ui.functionalities.weather.components.ChartContainer
import com.patrykandpatryk.vico.compose.axis.vertical.startAxis


@Composable
fun WeatherChart(
    modifier: Modifier = Modifier,
    historyState: HistoryState
) {
    val startAxis = startAxis(
        maxLabelCount = 5,
        valueFormatter = IntFormatAxisValueFormatter(),
        axis = null
    )

    LazyColumn() {
        item {
            historyState.temperature?.let { temperature ->
                val chartModel: MutableList<Pair<Float, Float>> = mutableListOf()
                temperature.forEachIndexed { index, fl ->
                    chartModel.add(
                        Pair(
                            index.toFloat(),
                            fl ?: 0F
                        )
                    )
                }
                ChartContainer(chartModel = chartModel, header = "Ad")
            }
        }
        item {
            historyState.temperature?.let { temperature ->
                val chartModel: MutableList<Pair<Float, Float>> = mutableListOf()
                temperature.forEachIndexed { index, fl ->
                    chartModel.add(
                        Pair(
                            index.toFloat(),
                            fl ?: 0F
                        )
                    )
                }
                ChartContainer(chartModel = chartModel, header = "Ad")
                /*Chart(
                    modifier = modifier,
                    chart = lineChart(),
                    chartModelProducer = ChartEntryModelProducer(entriesOf(*chartModel.toTypedArray())),
                    startAxis = startAxis,
                    bottomAxis = bottomAxis(
                        tickLength = 0.dp,
                        guideline = null
                    ),
                    marker = marker(),
                )*/
            }
        }
        /*    }
    item {
        historyState.pressure?.let { pressure ->
            LineGraph(
                xAxisData = listOf("Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat").map {
                    GraphData.String(it)
                }, // xAxisData : List<GraphData>, and GraphData accepts both Number and String types
                yAxisData = pressure.map { it ?: 0F },
                style = style2,
                header = { Text("lol") }
            )
        }
    }
    item {
        historyState.humidity?.let { humidity ->
            LineGraph(
                xAxisData = listOf("Sun", "Mon", "Tues", "Wed", "Thur", "Fri", "Sat").map {
                    GraphData.String(it)
                }, // xAxisData : List<GraphData>, and GraphData accepts both Number and String types
                yAxisData = humidity.map { it ?: 0F },
                style = style2,
                header = { Text("lol") }
            )
        }
    }*/

    }
}
