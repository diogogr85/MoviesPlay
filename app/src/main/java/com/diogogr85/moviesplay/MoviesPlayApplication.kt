package com.diogogr85.moviesplay

import android.app.Application
import com.diogogr85.moviesplay.di.apiServiceModules
import com.diogogr85.moviesplay.di.appModules
import com.diogogr85.moviesplay.di.networkModules
import com.diogogr85.moviesplay.di.repositoryModules
import com.diogogr85.moviesplay.di.useCaseModules
import com.diogogr85.moviesplay.di.viewModelModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MoviesPlayApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MoviesPlayApplication)
            modules(
                appModules,
                networkModules,
                apiServiceModules,
                repositoryModules,
                useCaseModules,
                viewModelModules,
            )
        }
    }
}