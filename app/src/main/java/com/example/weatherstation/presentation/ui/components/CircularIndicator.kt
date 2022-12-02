package com.example.weatherstation.presentation.ui.components


import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.weatherstation.data.util.Units
import com.example.weatherstation.presentation.ui.styles.textBlack
import com.example.weatherstation.presentation.ui.theme.BackgroundIndicatorColor
import com.example.weatherstation.presentation.ui.theme.ForegroundIndicatorColor
import com.example.weatherstation.presentation.ui.util.getFormattedString

@Composable
fun CircularIndicator(
    canvasSize: Dp = 100.dp,
    indicatorValue: Float = 0f,
    unit: Units,
    backgroundIndicatorStrokeWidth: Float = 20f,
    foregroundIndicatorStrokeWidth: Float = 20f,
    @DrawableRes icon: Int? = null,
    subText: String? = null,
    style: CircularIndicatorStyle = circularIndicatorStyle()
) {
    val minIndicatorValue = unit.minMaxValue.first
    val maxIndicatorValue = unit.minMaxValue.second
    var animatedIndicatorValue by remember {
        mutableStateOf(minIndicatorValue)
    }
    LaunchedEffect(indicatorValue) {
        animatedIndicatorValue = if (indicatorValue == 0f) minIndicatorValue else indicatorValue
    }

    val percentage = if (indicatorValue < minIndicatorValue || indicatorValue > maxIndicatorValue) {
        0f
    } else {
        ((animatedIndicatorValue - minIndicatorValue) / (maxIndicatorValue - minIndicatorValue)) * 100f
    }

    val sweepAngle by animateFloatAsState(
        targetValue = (2.4 * percentage).toFloat(),
        animationSpec = tween(1000)
    )

    Column(
        modifier = Modifier
            .padding(horizontal = style.horizontalPadding)
            .size(canvasSize)
            .drawBehind {
                val componentSize = size / 1.25f
                backgroundIndicator(
                    componentSize = componentSize,
                    indicatorColor = style.backgroundIndicatorColor,
                    indicatorStrokeWidth = backgroundIndicatorStrokeWidth
                )
                foregroundIndicator(
                    sweepAngle = sweepAngle,
                    componentSize = componentSize,
                    indicatorColor = style.foregroundIndicatorColor,
                    indicatorStrokeWidth = foregroundIndicatorStrokeWidth,
                )
            }
    ) {
        IndicatorContent(
            mainText = indicatorValue,
            subText = subText,
            minIndicator = minIndicatorValue.getFormattedString(),
            maxIndicator = maxIndicatorValue.getFormattedString(),
            icon = icon,
            style = style.indicatorContentStyle
        )
    }
}

fun DrawScope.backgroundIndicator(
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = 240f,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

fun DrawScope.foregroundIndicator(
    sweepAngle: Float,
    componentSize: Size,
    indicatorColor: Color,
    indicatorStrokeWidth: Float
) {
    drawArc(
        size = componentSize,
        color = indicatorColor,
        startAngle = 150f,
        sweepAngle = sweepAngle,
        useCenter = false,
        style = Stroke(
            width = indicatorStrokeWidth,
            cap = StrokeCap.Round
        ),
        topLeft = Offset(
            x = (size.width - componentSize.width) / 2f,
            y = (size.height - componentSize.height) / 2f
        )
    )
}

@Composable
private fun IndicatorContent(
    mainText: Float,
    minIndicator: String,
    maxIndicator: String,
    style: IndicatorContentStyle = indicatorContentStyle(),
    subText: String? = null,
    @DrawableRes icon: Int? = null,
) {
    Box(
        Modifier
            .fillMaxSize()
            .padding(
                start = style.horizontalPadding,
                top = style.topPadding,
                end = style.horizontalPadding
            )
    ) {
        Column(
            Modifier
                .fillMaxWidth()
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (mainText != 0f) {
                Text(text = mainText.getFormattedString(), style = style.textStyle)
                if (!subText.isNullOrEmpty()) {
                    Text(
                        text = subText,
                        style = style.textStyle
                    )
                }
            } else {
                Text(text = "N/A", style = style.textStyle)
                if (!subText.isNullOrEmpty()) {
                    Text(
                        text = subText,
                        style = style.textStyle
                    )
                }
            }
            MinMaxIndicatorSection(
                minIndicator = minIndicator,
                maxIndicator = maxIndicator,
                icon = icon,
                style = style.minMaxIndicatorSectionStyle
            )
        }
    }
}

@Composable
fun MinMaxIndicatorSection(
    minIndicator: String,
    maxIndicator: String,
    @DrawableRes icon: Int? = null,
    style: MinMaxIndicatorSectionStyle = minMaxIndicatorSectionStyle()
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = style.startPadding)
            .offset(y = style.yOffset)
    ) {
        Text(text = minIndicator, style = style.textStyle)
        if (icon != null) {
            Icon(
                modifier = Modifier
                    .size(style.iconSize)
                    .weight(1f),
                painter = painterResource(icon),
                tint = style.iconColor,
                contentDescription = null
            )
        } else {
            Spacer(Modifier.weight(1f))
        }
        Text(text = maxIndicator, style = style.textStyle)
    }
}

data class CircularIndicatorStyle(
    val backgroundIndicatorColor: Color,
    val foregroundIndicatorColor: Color,
    val contentTopPadding: Dp,
    val contentBottomPadding: Dp,
    val rowIndicatorStartPadding: Dp,
    val textStyle: TextStyle,
    val horizontalPadding: Dp,
    val indicatorContentStyle: IndicatorContentStyle
)

@Composable
fun circularIndicatorStyle(
    backgroundIndicatorColor: Color = BackgroundIndicatorColor,
    foregroundIndicatorColor: Color = ForegroundIndicatorColor,
    contentTopPadding: Dp = 120.dp,
    contentBottomPadding: Dp = 55.dp,
    rowIndicatorStartPadding: Dp = 5.dp,
    textStyle: TextStyle = textBlack,
    horizontalPadding: Dp = 8.dp,
    indicatorContentStyle: IndicatorContentStyle = indicatorContentStyle()
) = CircularIndicatorStyle(
    backgroundIndicatorColor = backgroundIndicatorColor,
    foregroundIndicatorColor = foregroundIndicatorColor,
    contentTopPadding = contentTopPadding,
    contentBottomPadding = contentBottomPadding,
    rowIndicatorStartPadding = rowIndicatorStartPadding,
    textStyle = textStyle,
    horizontalPadding = horizontalPadding,
    indicatorContentStyle = indicatorContentStyle
)

data class IndicatorContentStyle(
    val horizontalPadding: Dp,
    val topPadding: Dp,
    val textStyle: TextStyle,
    val minMaxIndicatorSectionStyle: MinMaxIndicatorSectionStyle,
)

@Composable
fun indicatorContentStyle(
    horizontalPadding: Dp = 10.dp,
    topPadding: Dp = 10.dp,
    textStyle: TextStyle = textBlack,
    minMaxIndicatorSectionStyle: MinMaxIndicatorSectionStyle = minMaxIndicatorSectionStyle(),
) = IndicatorContentStyle(
    horizontalPadding = horizontalPadding,
    topPadding = topPadding,
    textStyle = textStyle,
    minMaxIndicatorSectionStyle = minMaxIndicatorSectionStyle
)

data class MinMaxIndicatorSectionStyle(
    val startPadding: Dp,
    val yOffset: Dp,
    val textStyle: TextStyle,
    val iconSize: Dp,
    val iconColor: Color
)

@Composable
fun minMaxIndicatorSectionStyle(
    startPadding: Dp = 2.dp,
    yOffset: Dp = 13.dp,
    iconSize: Dp = 16.dp,
    iconColor: Color = BackgroundIndicatorColor,
    textStyle: TextStyle = textBlack
) = MinMaxIndicatorSectionStyle(
    startPadding = startPadding,
    yOffset = yOffset,
    iconSize = iconSize,
    iconColor = iconColor,
    textStyle = textStyle
)

/*
@Preview(showBackground = true, backgroundColor = 0x4298FF)
@Composable
fun CircularIndicatorPreview() {
    CircularIndicator(
        indicatorValue = 100f,
        subText = "hPa",
        minIndicator = "950",
        maxIndicator = "0",
        icon = R.drawable.sun_icon
    )
}*/
