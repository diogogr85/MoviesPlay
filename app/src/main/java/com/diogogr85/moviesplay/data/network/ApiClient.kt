package com.diogogr85.moviesplay.data.network

import com.diogogr85.moviesplay.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

fun provideLoggingInterceptor(): HttpLoggingInterceptor {
    val loggingInterceptor = HttpLoggingInterceptor()
    return loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
}

fun provideAuthInterceptor(): Interceptor {
    val authInterceptor = Interceptor { chain ->
        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer ${BuildConfig.TMDB_API_KEY}")
            .build()
        chain.proceed(request)
    }
    return authInterceptor
}

fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
    OkHttpClient.Builder()
        .addInterceptor(provideAuthInterceptor())
        .addInterceptor(loggingInterceptor)
        .build()

fun provideApiClient(okHttpClient: OkHttpClient): Retrofit =
    Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_API_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

fun <T> createService(retrofit: Retrofit, service: Class<T>): T = retrofit.create(service)