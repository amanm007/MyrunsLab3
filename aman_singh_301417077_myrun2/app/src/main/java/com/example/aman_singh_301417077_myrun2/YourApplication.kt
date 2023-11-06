package com.example.aman_singh_301417077_myrun2

import android.app.Application
import androidx.room.Room
import com.example.aman_singh_301417077_myrun2.AppDatabase
import com.example.aman_singh_301417077_myrun2.ExerciseRepository

class YourApplication : Application() {
    val database: AppDatabase by lazy {
        Room.databaseBuilder(this, AppDatabase::class.java, "exercise-database").build()
    }

    val repository: ExerciseRepository by lazy {
        ExerciseRepository(database.exerciseEntryDao())
    }
}