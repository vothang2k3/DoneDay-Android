package com.vothang.doneday.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class AppSettingImpl(private val context: Context) : AppSetting {
    // Tương tự như connection của Database
    private val Context.dataStoreAppSetting : DataStore<Preferences>
        by preferencesDataStore(name = "app-setting-pref")

    override val appSettingFLow: Flow<AppSettingData>
        get() = context.dataStoreAppSetting.data.map { pref ->
            AppSettingData( // Nếu chưa có Setting thì mặc định: false
                isNotificationOn = pref[AppSettingDataStoreKeys.IS_NOTIFICATION_ON] ?: false
            )
        }

    override suspend fun setIsNotificationOn(isNotificationOn: Boolean) = withContext(Dispatchers.IO){
        context.dataStoreAppSetting.edit { pref ->
            pref[AppSettingDataStoreKeys.IS_NOTIFICATION_ON] = isNotificationOn
        }
        Unit
    }

    override suspend fun getIsNotificationOn(): Boolean = withContext(Dispatchers.IO){
        /*        context.dataStoreAppSetting.data.map { pref ->
            pref[AppSettingDataStoreKeys.IS_NOTIFICATION_ON] ?: false
        }.first()*/

        // appSettingFLow.first().isNotificationOn

        context.dataStoreAppSetting.data.map {
            it[AppSettingDataStoreKeys.IS_NOTIFICATION_ON] ?: false
        }.first()
    }
}