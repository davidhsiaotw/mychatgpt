package com.example.mychatgpt.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserDataStore(private val context: Context) {
    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preference")
        val EMAIL = stringPreferencesKey("")
        val NAME = stringPreferencesKey("")
    }

    val getEmail: Flow<String> = context.dataStore.data.map { it[EMAIL] ?: "" }

    val getName: Flow<String> = context.dataStore.data.map { it[NAME] ?: "" }

    suspend fun saveEmail(email: String) = context.dataStore.edit { it[EMAIL] = email }

    suspend fun saveName(name: String) = context.dataStore.edit { it[NAME] = name }

}