package com.diogogr85.moviesplay.utils

import android.annotation.SuppressLint
import java.text.SimpleDateFormat

private const val DATE_PATTERN_US = "yyyy-MM-dd"
private const val DATE_PATTERN_BR = "dd/MM/yyyy"

@SuppressLint("SimpleDateFormat")
fun String.showReleaseDate(): String {
    val parser = SimpleDateFormat(DATE_PATTERN_US)
    val formatter = SimpleDateFormat(DATE_PATTERN_BR)
    val output: String = parser.parse(this)?.let {
        formatter.format(it)
    } ?: run {
        this
    }

    return output
}