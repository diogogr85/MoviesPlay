package com.diogogr85.moviesplay.di

import android.app.Application
import android.content.Context
import com.diogogr85.moviesplay.data.local.MoviePLayPrefs
import com.diogogr85.moviesplay.data.local.MoviePlayDatabase
import com.diogogr85.moviesplay.data.local.dao.MoviesDao
import com.diogogr85.moviesplay.data.local.provideDataBase
import com.diogogr85.moviesplay.data.network.createService
import com.diogogr85.moviesplay.data.network.movies.MoviesRepositoryImpl
import com.diogogr85.moviesplay.data.network.movies.MoviesService
import com.diogogr85.moviesplay.data.network.provideApiClient
import com.diogogr85.moviesplay.data.network.provideLoggingInterceptor
import com.diogogr85.moviesplay.data.network.provideOkHttpClient
import com.diogogr85.moviesplay.domain.presentation.movies.MoviesViewModel
import com.diogogr85.moviesplay.domain.repository.MoviesRepository
import com.diogogr85.moviesplay.domain.usecase.movies.MoviesUseCase
import com.diogogr85.moviesplay.domain.usecase.movies.MoviesUseCaseImpl
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

private const val SHARED_PREFS_FILENAME = "movieplay-sharedpreferences"

val appModules = module {
    single<MoviePlayDatabase> { provideDataBase(get()) }
    single<MoviesDao> { get<MoviePlayDatabase>().moviesDao() }
    single<MoviePLayPrefs> {
        MoviePLayPrefs(
            get<Application>()
                .getSharedPreferences(SHARED_PREFS_FILENAME, Context.MODE_PRIVATE)
        )
    }
}

val networkModules = module {
    factory<HttpLoggingInterceptor> { provideLoggingInterceptor() }
    factory<OkHttpClient> { provideOkHttpClient(get()) }
    single<Retrofit> { provideApiClient(get()) }
}

val apiServiceModules = module {
    factory<MoviesService> { createService(get(), MoviesService::class.java) }
}

val repositoryModules = module {
    factory<MoviesRepository> { MoviesRepositoryImpl(get(), get(), get()) }
}

val useCaseModules = module {
    factory<MoviesUseCase> { MoviesUseCaseImpl(get()) }
}

val viewModelModules = module {
    viewModel { MoviesViewModel(get()) }
}