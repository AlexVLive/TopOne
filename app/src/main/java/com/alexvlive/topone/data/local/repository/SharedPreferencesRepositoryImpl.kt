package com.alexvlive.topone.data.local.repository

import android.content.Context
import androidx.core.content.edit

class SharedPreferencesRepositoryImpl(context: Context): SharedPreferencesRepository {

    companion object{
        private const val PREFERENCES_NAME = "preferences"
        private const val SESSION_KEY = "key"
    }

    private val preferences = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE)

    override fun getSessionKey(): String = preferences.getString(SESSION_KEY, "") ?: ""

    override fun updateSessionKey(key: String) {
        preferences.edit {
            putString(SESSION_KEY, key)
        }
    }
}