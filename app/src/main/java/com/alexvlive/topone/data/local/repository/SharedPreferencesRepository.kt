package com.alexvlive.topone.data.local.repository

interface SharedPreferencesRepository {
    fun getSessionKey(): String
    fun updateSessionKey(key: String)
}