package com.diogogr85.moviesplay.ui.composable

import androidx.compose.runtime.mutableStateOf

object ScreenLoadingManager {
    private var _isLoading = mutableStateOf(false)
    val isLoading = _isLoading

    fun showLoading() {
        _isLoading.value = true
    }

    fun hideLoading() {
        _isLoading.value = false
    }
}