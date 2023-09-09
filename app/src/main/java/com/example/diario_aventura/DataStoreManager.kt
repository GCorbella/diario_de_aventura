package com.example.diario_aventura

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

object DataStoreManager {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private object DataStoreKeys {
        val KEY_SELECTED_PERSONAJE_ID = longPreferencesKey("selected_personaje_id")
        // Define otras claves aquí si es necesario
    }

    suspend fun saveSelectedCharacterId(context: Context, characterId: Long) {
        context.dataStore.edit { settings ->
            settings[DataStoreKeys.KEY_SELECTED_PERSONAJE_ID] = characterId
        }
    }

    fun getSelectedCharacterId(context: Context): Flow<Long?> {
        return context.dataStore.data.map { preferences ->
            preferences[DataStoreKeys.KEY_SELECTED_PERSONAJE_ID]
        }
    }
}
