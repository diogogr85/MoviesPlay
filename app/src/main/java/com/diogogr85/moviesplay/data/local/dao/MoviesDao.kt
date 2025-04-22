package com.diogogr85.moviesplay.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import com.diogogr85.moviesplay.data.local.entity.MovieEntityDB

@Dao
interface MoviesDao {
    @Insert(onConflict = REPLACE)
    suspend fun insertAll(movies: List<MovieEntityDB>)

    @Query("SELECT * FROM MovieEntityDB WHERE type = :type")
    fun getMoviesByType(type: String): List<MovieEntityDB>

    @Query("DELETE FROM MovieEntityDB WHERE type = :type")
    fun deleteByType(type: String)
}
