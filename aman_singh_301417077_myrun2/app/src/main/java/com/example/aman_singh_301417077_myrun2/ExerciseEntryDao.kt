package com.example.aman_singh_301417077_myrun2

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.lifecycle.LiveData

@Dao
interface ExerciseEntryDao {
    @Insert
    fun insertEntry(entry: ExerciseEntry)

    @Delete
    fun deleteEntry(entry: ExerciseEntry)

    @Query("SELECT * FROM exercise_entries WHERE id = :id")
    fun getEntryById(id: Long): ExerciseEntry

    @Query("SELECT * FROM exercise_entries")
    fun getAllEntries(): LiveData<List<ExerciseEntry>>
}