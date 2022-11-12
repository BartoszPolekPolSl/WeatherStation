package com.example.weatherstation.presentation.ui.components

import androidx.compose.runtime.Composable
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState

@Composable
fun SwipeToRefresh(
    state: SwipeRefreshState,
    onRefresh: () -> Unit,
    content: @Composable () -> Unit
) {
    SwipeRefresh(state = state, onRefresh = { onRefresh() }) {
        content()
    }
}