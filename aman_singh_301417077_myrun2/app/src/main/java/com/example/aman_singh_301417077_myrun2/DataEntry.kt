package com.example.aman_singh_301417077_myrun2

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class DataEntry : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.data_entry)


        val datePickerEditText: EditText = findViewById(R.id.datePickerEditText)
        val timePickerEditText: EditText = findViewById(R.id.timePickerEditText)

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
// ...

    }
}
