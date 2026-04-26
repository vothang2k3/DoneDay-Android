package com.vothang.doneday.repository

import com.vothang.doneday.database.entity.TaskCollection
import com.vothang.doneday.database.entity.TaskEntity

interface TaskRepo {
    suspend fun getTaskCollections() : List<TaskCollection>
    suspend fun getTasksByCollectionId(collectionId: Int) : List<TaskEntity>
    suspend fun addTaskCollection(title: String) : TaskCollection?
    suspend fun addTask(title: String, collectionId: Int) : TaskEntity?
    suspend fun updateTask(task: TaskEntity) : Boolean
    suspend fun updateTaskCollection(taskCollection: TaskCollection) : Boolean
}