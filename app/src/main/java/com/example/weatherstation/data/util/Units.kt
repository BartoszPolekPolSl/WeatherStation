package com.example.weatherstation.data.util

enum class Units(
    val symbol: String,
    val requestParameter: String,
    val minMaxValue: Pair<Float, Float>
) {
    CELSIUS("C", "c", Pair(-90f, 55f)),
    FAHRENHEIT("F", "f", Pair(-130f, 130f)),
    HECTOPASCAL("hPa", "h", Pair(950f, 1100f)),
    BAR("bar", "b", Pair(0.95f, 1.1f)),
    PSI("psi", "p", Pair(13.77f, 15.95f)),
    PERCENT("%", "pe", Pair(0f, 100f));

    companion object {
        fun findBySymbol(symbol: String): Units? = Units.values().find { it.symbol == symbol }
    }
}
