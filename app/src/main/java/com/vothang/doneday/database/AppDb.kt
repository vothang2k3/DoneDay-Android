package com.vothang.doneday.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vothang.doneday.database.dao.TaskDao
import com.vothang.doneday.database.entity.TaskCollection
import com.vothang.doneday.database.entity.TaskEntity

private const val DATABASE_NAME = "app.db"
private const val DATABASE_VERSION = 1

@Database(
    entities = [TaskEntity::class, TaskCollection::class],
    version = 1
)
abstract class AppDb : RoomDatabase() {
    abstract fun taskDAO(): TaskDao

    companion object {
        private var instance: AppDb? = null

        operator fun invoke(context: Context): AppDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDb = Room.databaseBuilder(
            context,
            AppDb::class.java,
            DATABASE_NAME
        ).build()
    }
}