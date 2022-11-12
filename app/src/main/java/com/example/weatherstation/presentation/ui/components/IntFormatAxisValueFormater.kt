package com.example.weatherstation.presentation.ui.components

import com.patrykandpatryk.vico.core.axis.AxisPosition
import com.patrykandpatryk.vico.core.axis.formatter.AxisValueFormatter
import com.patrykandpatryk.vico.core.formatter.DecimalFormatValueFormatter
import java.math.RoundingMode
import java.text.DecimalFormat

class IntFormatAxisValueFormatter<Position : AxisPosition>(decimalFormat: DecimalFormat) :
    AxisValueFormatter<Position>, DecimalFormatValueFormatter(decimalFormat = decimalFormat) {
    constructor() : this(IntFormatValueFormatter.DEF_FORMAT)

    constructor(
        pattern: String,
        roundingMode: RoundingMode = RoundingMode.HALF_UP,
    ) : this(IntFormatValueFormatter.getDecimalFormat(pattern, roundingMode))
}