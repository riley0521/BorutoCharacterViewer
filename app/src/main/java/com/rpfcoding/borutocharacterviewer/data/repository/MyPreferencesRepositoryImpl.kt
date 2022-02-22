package com.rpfcoding.borutocharacterviewer.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.rpfcoding.borutocharacterviewer.domain.repository.MyPreferencesRepository
import com.rpfcoding.borutocharacterviewer.util.Constants.PREFERENCES_NAME
import com.rpfcoding.borutocharacterviewer.util.Constants.PREF_ON_BOARDING_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class MyPreferencesRepositoryImpl(
    context: Context
) : MyPreferencesRepository {

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = PREF_ON_BOARDING_KEY)
    }

    private val dataStore = context.dataStore

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { pref ->
            pref[PreferencesKey.onBoardingKey] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore
            .data
            .catch {
                emit(emptyPreferences())
            }
            .map { pref ->
                val onBoardingState = pref[PreferencesKey.onBoardingKey] ?: false
                onBoardingState
            }
    }
}