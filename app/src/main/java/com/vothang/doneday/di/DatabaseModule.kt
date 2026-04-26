package com.vothang.doneday.di

import android.content.Context
import com.vothang.doneday.database.AppDB
import com.vothang.doneday.database.dao.TaskDAO
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideTaskDao(appDB: AppDB) : TaskDAO {
        return appDB.taskDao()
    }

    @Singleton
    @Provides
    fun provideAppDB(@ApplicationContext context : Context) : AppDB {
        return AppDB.invoke(context)
    }
}