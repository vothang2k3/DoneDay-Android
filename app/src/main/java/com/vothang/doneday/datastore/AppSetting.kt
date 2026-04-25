package com.vothang.doneday.datastore

import kotlinx.coroutines.flow.Flow

interface AppSetting {
    val appSettingFLow: Flow<AppSettingData>

    // Cần suspend function để Read/Write Local File
    suspend fun setIsNotificationOn(isNotificationOn: Boolean)

    suspend fun getIsNotificationOn(): Boolean
}