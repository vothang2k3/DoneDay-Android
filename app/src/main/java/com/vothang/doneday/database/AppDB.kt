package com.vothang.doneday.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.vothang.doneday.database.dao.TaskDAO
import com.vothang.doneday.database.entity.TaskEntity
import com.vothang.doneday.database.entity.TaskCollection

@Database(
    entities = [TaskCollection::class, TaskEntity::class],
    version = 1
)
abstract class AppDB : RoomDatabase() {
    abstract fun taskDao() : TaskDAO

    companion object {
        private var instance : AppDB? = null

        operator fun invoke(context : Context) : AppDB {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context) : AppDB = Room.databaseBuilder(
            context,
            AppDB::class.java,
            "app.db"
        ).build()
    }
}