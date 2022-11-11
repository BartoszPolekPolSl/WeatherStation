package com.example.weatherstation.presentation.ui.weather


import androidx.annotation.DrawableRes
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.weatherstation.presentation.ui.styles.textBlack
import com.example.weatherstation.presentation.ui.theme.BackgroundIndicatorColor
import com.example.weatherstation.presentation.ui.theme.ForegroundIndicatorColor

@Composable
fun CircularIndicator(
    canvasSize: Dp = 80.dp,
    indicatorValue: Int = 0,
    maxIndicatorValue: Int = 100,
    backgroundIndicatorStrokeWidth: Float = 20f,
    foregroundIndicatorStrokeWidth: Float = 20f,
    minIndicator: String,
    maxIndicator: String,
    @DrawableRes icon: Int? = null,
    subText: String? = null,
    style: CircularIndicatorStyle = circularIndicatorStyle()
) {
    var animatedIndicatorValue by remember {
        mutableStateOf(0f)
    }
    LaunchedEffect(indicatorValue) {
        animatedIndicatorValue = indicatorValue.toFloat()
    }

    val percentage = (animatedIndicatorValue / maxIndicatorValue) * 100

    val sweepAngle by animateFloatAsState(
        targetValue = (2.4 * percentage).toFloat(),
        animationSpec = tween(1000)
    )

    val receivedValue by animateIntAsState(
        targetValue = animatedIndicatorValue.toInt(),
        animationSpec = tween(1000)
    )

    Column(
        modifier = Modifier
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
            },
        /*        horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center*/
    ) {
        IndicatorContent(
            mainText = receivedValue,
            subText = subText,
            minIndicator = minIndicator,
            maxIndicator = maxIndicator,
            icon = icon,
            style = style
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
    mainText: Int,
    minIndicator: String,
    maxIndicator: String,
    style: CircularIndicatorStyle,
    subText: String? = null,
    @DrawableRes icon: Int? = null
) {
    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = mainText.toString(), style = style.textStyle)
            if (!subText.isNullOrEmpty()) {
                Text(
                    text = subText,
                    style = style.textStyle
                )
            }
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 2.dp)
            ) {
                Text(text = minIndicator, style = style.textStyle)
                if (icon != null) {
                    Image(painter = painterResource(icon), contentDescription = null)
                } else {
                    Spacer(Modifier)
                }
                Text(text = maxIndicator, style = style.textStyle)
            }
        }
    }

}

data class CircularIndicatorStyle(
    val backgroundIndicatorColor: Color,
    val foregroundIndicatorColor: Color,
    val contentTopPadding: Dp,
    val contentBottomPadding: Dp,
    val rowIndicatorStartPadding: Dp,
    val textStyle: TextStyle
)

@Composable
fun circularIndicatorStyle(
    backgroundIndicatorColor: Color = BackgroundIndicatorColor,
    foregroundIndicatorColor: Color = ForegroundIndicatorColor,
    contentTopPadding: Dp = 120.dp,
    contentBottomPadding: Dp = 55.dp,
    rowIndicatorStartPadding: Dp = 5.dp,
    textStyle: TextStyle = textBlack
) = CircularIndicatorStyle(
    backgroundIndicatorColor = backgroundIndicatorColor,
    foregroundIndicatorColor = foregroundIndicatorColor,
    contentTopPadding = contentTopPadding,
    contentBottomPadding = contentBottomPadding,
    rowIndicatorStartPadding = rowIndicatorStartPadding,
    textStyle = textStyle
)

@Preview(showBackground = true, backgroundColor = 0x4298FF)
@Composable
fun CircularIndicatorPreview() {
    CircularIndicator(
        indicatorValue = 100,
        subText = "hPa",
        minIndicator = "0",
        maxIndicator = "100"
    )
}