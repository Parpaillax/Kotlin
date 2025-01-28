package com.example.demokotlin.auth.viewmodel

import android.content.Context
import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.runBlocking

private const val PREFERENCES_NAME = "DATA_STORE_PREFERENCES_NAME"

private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(
    name = PREFERENCES_NAME
)

class SettingsDataStore(private val context: Context) {
    // Clé pour stocker le token
    companion object {
        private val TOKEN_KEY = stringPreferencesKey("jwt_token")
    }

    // Fonction pour sauvegarder le token
    suspend fun saveToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun readFromDataStore(): String {
        val preferences = context.dataStore.data.first()
        return preferences[TOKEN_KEY] ?: "Default Value"
    }

    // Fonction pour supprimer le token (par exemple lors de la déconnexion)
    suspend fun clearToken() {
        context.dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

    fun isAuthenticated(): Boolean {
        return runBlocking {
            val token = context.dataStore.data
                .map { preferences -> preferences[TOKEN_KEY] }
                .first() // Récupère la valeur actuelle
            Log.d("Token", token.toString())
            token != null && token.isNotBlank()
        }
    }
}