package com.diogogr85.moviesplay.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class MovieEntityDB(
    @PrimaryKey val id: Int,
    @ColumnInfo("title") val title: String,
    @ColumnInfo("original_title") val originalTitle: String,
    @ColumnInfo("overview") val description: String,
    @ColumnInfo("poster_path") val posterPath: String,
    @ColumnInfo("vote_average") val voteAverage: Double,
    @ColumnInfo("release_date") val releaseDate: String,
    @ColumnInfo("type") val type: String
)

enum class MovieType {
    POPULAR,
    UPCOMING
}