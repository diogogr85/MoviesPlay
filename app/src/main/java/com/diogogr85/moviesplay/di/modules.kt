package com.diogogr85.moviesplay.di

import com.diogogr85.moviesplay.data.network.createService
import com.diogogr85.moviesplay.data.network.movies.MoviesRepositoryImpl
import com.diogogr85.moviesplay.data.network.movies.MoviesService
import com.diogogr85.moviesplay.data.network.provideApiClient
import com.diogogr85.moviesplay.data.network.provideLoggingInterceptor
import com.diogogr85.moviesplay.data.network.provideOkHttpClient
import com.diogogr85.moviesplay.domain.presentation.movies.MoviesViewModel
import com.diogogr85.moviesplay.domain.repository.MoviesRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val appModules = module {
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
    factory<MoviesRepository> { MoviesRepositoryImpl(get()) }
}

val viewModelModules = module {
    viewModel { MoviesViewModel(get()) }
}