package com.example.weatherstation.presentation.ui.components

import com.patrykandpatryk.vico.core.chart.values.ChartValues
import com.patrykandpatryk.vico.core.formatter.ValueFormatter
import java.math.RoundingMode
import java.text.DecimalFormat

class IntFormatValueFormatter(private val decimalFormat: DecimalFormat) : ValueFormatter {

    override fun formatValue(
        value: Float,
        chartValues: ChartValues,
    ): String = decimalFormat.format(value)

    companion object {

        const val DEF_FORMAT: String = "#;−#"

        fun getDecimalFormat(
            pattern: String,
            roundingMode: RoundingMode,
        ): DecimalFormat = DecimalFormat(pattern).apply {
            this.roundingMode = roundingMode
        }
    }
}