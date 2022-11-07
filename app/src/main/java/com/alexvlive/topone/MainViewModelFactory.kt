package com.alexvlive.topone

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alexvlive.topone.data.local.repository.SharedPreferencesRepository
import com.alexvlive.topone.data.remote.repository.LastFmRepository

class MainViewModelFactory(
    private val lastFmRepository: LastFmRepository,
    private val sharedPreferencesRepository: SharedPreferencesRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            lastFmRepository = lastFmRepository,
            sharedPreferencesRepository = sharedPreferencesRepository
        ) as T
    }
}