package com.example.aman_singh_301417077_myrun2

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class DataEntry : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var exerciseEntryDao: ExerciseEntryDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.data_entry)

        // Initialize the database and DAO
        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "exercise-database"
        ).build()
        exerciseEntryDao = db.exerciseEntryDao()

        val datePickerEditText: EditText = findViewById(R.id.datePickerEditText)
        val timePickerEditText: EditText = findViewById(R.id.timePickerEditText)
        val durationEditText: EditText = findViewById(R.id.durationEditText)
        val distanceEditText: EditText = findViewById(R.id.distanceEditText)
        val caloriesEditText: EditText = findViewById(R.id.caloriesEditText)
        val heartRateEditText: EditText = findViewById(R.id.heartRateEditText)
        val commentsEditText: EditText = findViewById(R.id.commentsEditText)
        val saveButton: Button = findViewById(R.id.saveButton)

        datePickerEditText.setOnClickListener {

            val calendar = Calendar.getInstance()
            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            DatePickerDialog(this, { _, y, m, d ->
                val selectedDate = "$d/$m/$y"
                datePickerEditText.setText(selectedDate)
            }, year, month, day).show()
        }

        timePickerEditText.setOnClickListener {
            // Show Time Picker Dialog
            val calendar = Calendar.getInstance()
            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)
            TimePickerDialog(this, { _, h, m ->
                val selectedTime = "$h:$m"
                timePickerEditText.setText(selectedTime)
            }, hour, minute, true).show()
        }
        saveButton.setOnClickListener {
            val date = datePickerEditText.text.toString()
            val time = timePickerEditText.text.toString()
            val dateTimeFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
            val dateTime = dateTimeFormat.parse("$date $time")?.time ?: return@setOnClickListener

            val duration = durationEditText.text.toString().toDoubleOrNull() ?: 0.0
            val distance = distanceEditText.text.toString().toDoubleOrNull() ?: 0.0
            val calories = caloriesEditText.text.toString().toIntOrNull() ?: 0
            val heartRate = heartRateEditText.text.toString().toIntOrNull() ?: 0
            val comments = commentsEditText.text.toString()

            // Create an ExerciseEntry object with all the collected data
            val exerciseEntry = ExerciseEntry(
                inputType = 0, // Replace with actual inputType variable
                activityType = 0, // Replace with actual activityType variable
                dateTime = dateTime,
                duration = duration,
                distance = distance,
                avgPace = 0.0, // Replace with actual avgPace variable if you have one
                avgSpeed = 0.0, // Replace with actual avgSpeed variable if you have one
                calorie = calories.toDouble(),
                climb = 0.0, // Replace with actual climb variable if you have one
                heartRate = heartRate.toDouble(),
                comment = comments,
                locationList = ByteArray(0) // Replace with actual location data if available
            )

            // Call a method to save this entry to the database
            saveEntryToDatabase(exerciseEntry)
        }
    }

    private fun saveEntryToDatabase(exerciseEntry: ExerciseEntry) {
        CoroutineScope(Dispatchers.IO).launch {
            // Insert the exercise entry into the database
            exerciseEntryDao.insertEntry(exerciseEntry)

            // Switch back to the main thread if you need to update the UI
            CoroutineScope(Dispatchers.Main).launch {
                Toast.makeText(applicationContext, "Entry saved!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}



