package com.diogogr85.moviesplay.data.local

import android.content.SharedPreferences
import androidx.core.content.edit

class MoviePLayPrefs(private val sharedPrefs: SharedPreferences) {
    var dbTimestamp: Long
        get() = sharedPrefs.getLong(DB_EXPIRATION_TIME_KEY, -1)
        set(value) = sharedPrefs.edit { putLong(DB_EXPIRATION_TIME_KEY, value) }
}

private const val DB_EXPIRATION_TIME_KEY = "db-expiration-timestamp"