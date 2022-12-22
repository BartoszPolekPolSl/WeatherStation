package com.example.weatherstation.presentation.ui.functionalities.weather.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.weatherstation.R
import com.example.weatherstation.data.dto.Station
import com.example.weatherstation.presentation.ui.styles.textMedium
import com.example.weatherstation.presentation.ui.styles.textSmall
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.time.Duration
import kotlin.time.Duration.Companion.milliseconds

@Composable
fun SearchField(
    stations: List<Station>,
    query: String,
    onQueryChange: (String) -> Unit,
    onClearIconClick: () -> Unit,
    onStationClick: (Station) -> Unit,
    modifier: Modifier = Modifier,
    style: SearchFieldStyle = searchFieldStyle()
) {
    val focusManager = LocalFocusManager.current
    val focusState: MutableState<FocusState?> = remember { mutableStateOf(null) }

    LazyColumn(modifier = modifier) {
        item {
            Overlay(
                isClearIconVisible = focusState.value?.isFocused == true && query.isNotEmpty(),
                onClearIconClick = {
                    onClearIconClick()
                    focusManager.clearFocus()
                },
                style = style,
                modifier = modifier
            ) {
                BasicTextField(
                    value = query,
                    onValueChange = onQueryChange,
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                    keyboardActions = KeyboardActions { focusManager.clearFocus() },
                    singleLine = true,
                    textStyle = style.textStyle,
                    decorationBox = decorationBox(query, style),
                    modifier = Modifier
                        .weight(1f)
                        .onFocusChanged { focusState.value = it }
                        .onImeVisibilityChanged { imeVisible ->
                            if (focusState.value?.isFocused == true && !imeVisible) {
                                focusManager.clearFocus()
                            }
                        }
                )
            }
        }
        if (stations.isNotEmpty()) {
            itemsIndexed(stations) { index, station ->
                Column(
                    Modifier
                        .padding(horizontal = 5.dp)
                        .clip(
                            if (stations.lastIndex == index) RoundedCornerShape(
                                bottomStart = 4.dp,
                                bottomEnd = 4.dp
                            ) else RoundedCornerShape(0.dp)
                        )
                        .fillMaxWidth()
                        .background(Color.White)
                        .clickable {
                            onStationClick(station)
                        }
                ) {
                    Text(text = station.localization, style = textMedium.copy(color = Color.Black))
                    Text(text = station.name, style = textMedium.copy(color = Color.Gray))
                    if (stations.lastIndex != index) {
                        Divider(color = Color.Gray)
                    }
                }
            }
        }
    }

}

@Composable
private fun decorationBox(
    query: String,
    style: SearchFieldStyle
): @Composable (innerTextField: @Composable () -> Unit) -> Unit =
    {
        Placeholder(
            visible = query.isEmpty(),
            innerTextField = it,
            style = style
        )
    }

@Composable
private fun <T> DebouncedEffect(
    key: T,
    timeout: Duration = 600.milliseconds,
    onTrigger: (T) -> Unit
) {
    var queryJob by remember { mutableStateOf<Job?>(null) }
    LaunchedEffect(key) {
        if (queryJob?.isActive == true) {
            queryJob?.cancel()
            return@LaunchedEffect
        }
        queryJob = launch {
            delay(timeout)
            onTrigger(key)
        }
    }
}

@Composable
private fun Overlay(
    isClearIconVisible: Boolean,
    onClearIconClick: () -> Unit,
    style: SearchFieldStyle,
    modifier: Modifier,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(style.height)
            .clip(style.shape)
            .background(style.backgroundColor)
    ) {
        LeadingIcon(style = style)
        content()
        TrailingIcon(
            visible = isClearIconVisible,
            onClick = onClearIconClick,
            style = style
        )
    }
}

@Composable
private fun Placeholder(
    visible: Boolean,
    innerTextField: @Composable () -> Unit,
    style: SearchFieldStyle
) {
    Box(contentAlignment = Alignment.CenterStart) {
        innerTextField()
        if (visible) {
            Text(
                text = "Search",
                style = style.placeholderTextStyle,
                modifier = Modifier.padding(style.placeholderPadding)
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
private fun Modifier.onImeVisibilityChanged(
    onImeVisibilityChanged: (Boolean) -> Unit
) = composed {
    val isImeVisible = WindowInsets.isImeVisible
    LaunchedEffect(isImeVisible) {
        onImeVisibilityChanged(isImeVisible)
    }
    this
}

@Composable
private fun LeadingIcon(style: SearchFieldStyle) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(LocalViewConfiguration.current.minimumTouchTargetSize)
    ) {
        Icon(
            painter = painterResource(R.drawable.search),
            tint = style.iconColor,
            contentDescription = null
        )
    }
}

@Composable
private fun TrailingIcon(
    visible: Boolean,
    onClick: () -> Unit,
    style: SearchFieldStyle
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(tween(ANIMATION_DURATION)),
        exit = fadeOut(tween(ANIMATION_DURATION))
    ) {
        IconButton(onClick = onClick) {
            Icon(
                painter = painterResource(R.drawable.clear),
                tint = style.iconColor,
                contentDescription = null
            )
        }
    }
}

@Immutable
data class SearchFieldStyle(
    val height: Dp,
    val shape: Shape,
    val backgroundColor: Color,
    val iconColor: Color,
    val placeholderPadding: PaddingValues,
    val textStyle: TextStyle,
    val placeholderTextStyle: TextStyle
)

@Composable
fun searchFieldStyle() = SearchFieldStyle(
    height = 36.dp,
    shape = RoundedCornerShape(8.dp),
    placeholderPadding = PaddingValues(start = 3.dp),
    iconColor = Color.Gray,
    textStyle = textMedium.copy(color = (Color.Black)),
    placeholderTextStyle = textSmall,
    backgroundColor = Color.White
)

private const val ANIMATION_DURATION = 250
