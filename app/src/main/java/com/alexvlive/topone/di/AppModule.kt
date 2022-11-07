package com.alexvlive.topone.di

import com.alexvlive.topone.MainViewModelFactory
import com.alexvlive.topone.data.local.repository.SharedPreferencesRepository
import com.alexvlive.topone.data.remote.repository.LastFmRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    fun provideMainViewModelFactory(
            lastFmRepository: LastFmRepository,
            sharedPreferencesRepository: SharedPreferencesRepository
    ): MainViewModelFactory {
        return MainViewModelFactory(
            lastFmRepository = lastFmRepository,
            sharedPreferencesRepository = sharedPreferencesRepository
        )
    }
}