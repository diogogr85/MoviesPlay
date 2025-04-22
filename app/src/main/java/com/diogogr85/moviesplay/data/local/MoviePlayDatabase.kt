package com.diogogr85.moviesplay.data.local

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.diogogr85.moviesplay.data.local.MoviePlayDatabase.Companion.DATABASE_NAME
import com.diogogr85.moviesplay.data.local.dao.MoviesDao
import com.diogogr85.moviesplay.data.local.entity.MovieEntityDB

@Database(entities = [MovieEntityDB::class], version = 1)
abstract class MoviePlayDatabase: RoomDatabase() {

    abstract fun moviesDao(): MoviesDao

    companion object {
        const val DATABASE_NAME = "movieplay_database"
    }

}
fun provideDataBase(application: Application): MoviePlayDatabase =
    Room.databaseBuilder(
        application,
        MoviePlayDatabase::class.java,
        DATABASE_NAME
    ).fallbackToDestructiveMigration(true).build()
