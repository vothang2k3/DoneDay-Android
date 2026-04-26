package com.vothang.doneday.di

import com.vothang.doneday.database.dao.TaskDAO
import com.vothang.doneday.repository.TaskRepo
import com.vothang.doneday.repository.TaskRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TaskRepoModule {

    @Singleton
    @Provides
    fun provideTaskRepo(taskDAO: TaskDAO): TaskRepo {
        return TaskRepoImpl(taskDAO)
    }
}