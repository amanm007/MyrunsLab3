package com.example.aman_singh_301417077_myrun2

import androidx.lifecycle.LiveData

class ExerciseRepository(private val exerciseEntryDao: ExerciseEntryDao) {

    val allEntries: LiveData<List<ExerciseEntry>> = exerciseEntryDao.getAllEntries()


    suspend fun insert(entry: ExerciseEntry) {
        exerciseEntryDao.insertEntry(entry)
    }

    suspend fun delete(entry: ExerciseEntry) {
        exerciseEntryDao.deleteEntry(entry)
    }
}
