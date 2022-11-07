package com.alexvlive.topone.di

import android.content.Context
import com.alexvlive.topone.BuildConfig
import com.alexvlive.topone.data.local.repository.SharedPreferencesRepository
import com.alexvlive.topone.data.local.repository.SharedPreferencesRepositoryImpl
import com.alexvlive.topone.data.remote.api.LastFmAPI
import com.alexvlive.topone.data.remote.repository.LastFmRepository
import com.alexvlive.topone.data.remote.repository.LastFmRepositoryImpl
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideLastFmAPI(): LastFmAPI = Retrofit.Builder()
            .baseUrl("http://ws.audioscrobbler.com")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                        if(BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
                        else HttpLoggingInterceptor.Level.NONE
                    })
                    .build()
            )
            .build()
            .create(LastFmAPI::class.java)

    @Provides
    @Singleton
    fun provideRepositoryLastFm(lastFmAPI: LastFmAPI) : LastFmRepository =
        LastFmRepositoryImpl( lastFmApi = lastFmAPI )

    @Provides
    @Singleton
    fun provideRepositorySharedPreferences(context: Context) : SharedPreferencesRepository =
        SharedPreferencesRepositoryImpl(context = context)
}