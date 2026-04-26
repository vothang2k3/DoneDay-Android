package com.vothang.doneday.repository

import com.vothang.doneday.database.dao.TaskDAO
import com.vothang.doneday.database.entity.TaskCollection
import com.vothang.doneday.database.entity.TaskEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.Calendar

class TaskRepoImpl(
    private val taskDAO: TaskDAO
) : TaskRepo {
    override suspend fun getTaskCollections(): List<TaskCollection> = withContext(Dispatchers.IO) {
        taskDAO.getTaskCollections()
    }

    override suspend fun getTasksByCollectionId(collectionId: Int): List<TaskEntity> = withContext(Dispatchers.IO) {
        taskDAO.getTask(collectionId)
    }

    override suspend fun addTaskCollection(title: String): TaskCollection? = withContext(Dispatchers.IO) {
        val taskCollection = TaskCollection(title = title, updatedAt = Calendar.getInstance().timeInMillis)
        val id = taskDAO.insertTaskCollection(taskCollection)
        if (id >0 ) {
            taskCollection.copy(
                id = id.toInt()
            )
        } else {
            null
        }
    }

    override suspend fun addTask(title: String, collectionId: Int): TaskEntity? = withContext(Dispatchers.IO){
        val taskEntity = TaskEntity(
            title = title,
            isFavorite = false,
            isCompleted = false,
            collectionId = collectionId,
            updatedAt = Calendar.getInstance().timeInMillis,
        )
        val id = taskDAO.insertTask(taskEntity)
        if(id > 0) {
            taskEntity.copy(
                id = id.toInt()
            )
        } else {
            null
        }
    }

    override suspend fun updateTask(task: TaskEntity): Boolean = withContext(Dispatchers.IO){
        taskDAO.updateTask(task) > 0
    }

    override suspend fun updateTaskCollection(taskCollection: TaskCollection): Boolean  = withContext(Dispatchers.IO){
        taskDAO.updateTaskCollection(taskCollection) > 0
    }
}
