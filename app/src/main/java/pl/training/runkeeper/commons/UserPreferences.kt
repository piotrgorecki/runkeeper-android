package pl.training.runkeeper.commons

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences(private val context: Context) {

    private val cityKey = stringPreferencesKey("cityName")

    private val Context.dataStore by preferencesDataStore(name = "settings")

    suspend fun saveCity(city: String) {
        context.dataStore.edit { it[cityKey] = city }
    }

    fun getCity(): Flow<String> = context.dataStore.data.map { it[cityKey] ?: "" }

}