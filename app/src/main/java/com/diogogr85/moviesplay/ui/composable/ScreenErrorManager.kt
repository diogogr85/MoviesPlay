package com.diogogr85.moviesplay.ui.composable

import androidx.compose.runtime.mutableStateOf

object ScreenErrorManager {
    private var _hasError = mutableStateOf(false)
    val hasError = _hasError

    fun showErrorScreen() {
        _hasError.value = true
    }

    fun hideErrorScreen() {
        _hasError.value = false
    }
}