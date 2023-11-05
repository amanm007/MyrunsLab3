package com.example.aman_singh_301417077_myrun2


import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class UserProfile : AppCompatActivity() {

    //MY MYRUNS 1 ASSIGNMENT

    lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile)

        // initializing our SharedPreferences
        sharedPreferences = getSharedPreferences("ProfileData", MODE_PRIVATE)

        // getting  references to EditText fields
        val nameEditText = findViewById<EditText>(R.id.etNameLabel)
        val emailEditText = findViewById<EditText>(R.id.edetEmailLabel)
        val phoneEditText = findViewById<EditText>(R.id.etPhoneLabel)
        val classEditText = findViewById<EditText>(R.id.etClassLabel)
        val majorEditText = findViewById<EditText>(R.id.etMajorLabel)

        // getting references to Buttons
        val saveButton = findViewById<Button>(R.id.appsavelabel)
        val clearButton = findViewById<Button>(R.id.appclearlabel)


        // getting  reference to RadioGroup
        val genderRadioGroup = findViewById<RadioGroup>(R.id.genderRadioGroup)

        // loading saved data
        loadData(nameEditText, emailEditText, phoneEditText, classEditText, majorEditText, genderRadioGroup)

        // innitializing  a variable to keep track of the last checked radio button
        var lastCheckedId = -1

        genderRadioGroup.setOnCheckedChangeListener { group, checkedId ->
            if (lastCheckedId == checkedId) {

                group.clearCheck()
                lastCheckedId = -1
            } else {
                // Update the last checked id
                lastCheckedId = checkedId
            }
        }

        saveButton.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.putString("Name", nameEditText.text.toString())
            editor.putString("Email", emailEditText.text.toString())
            editor.putString("Phone", phoneEditText.text.toString())
            editor.putString("Class", classEditText.text.toString())
            editor.putString("Major", majorEditText.text.toString())
            editor.putInt("Gender", genderRadioGroup.checkedRadioButtonId)
            editor.apply()

            Toast.makeText(this, "Profile saved successfully!", Toast.LENGTH_SHORT).show()
            finish()
        }

        clearButton.setOnClickListener {
            sharedPreferences.edit().clear().apply()
            nameEditText.text.clear()
            emailEditText.text.clear()
            phoneEditText.text.clear()
            classEditText.text.clear()
            majorEditText.text.clear()
            genderRadioGroup.clearCheck()

            Toast.makeText(this, "Profile cleared!", Toast.LENGTH_SHORT).show()

        }
    }


    private fun loadData(nameEditText: EditText, emailEditText: EditText, phoneEditText: EditText, classEditText: EditText, majorEditText: EditText, genderRadioGroup: RadioGroup) {
        val name = sharedPreferences.getString("Name", "")
        val email = sharedPreferences.getString("Email", "")
        val phone = sharedPreferences.getString("Phone", "")
        val clazz = sharedPreferences.getString("Class", "")
        val major = sharedPreferences.getString("Major", "")
        val genderId = sharedPreferences.getInt("Gender", -1)

        nameEditText.setText(name)
        emailEditText.setText(email)
        phoneEditText.setText(phone)
        classEditText.setText(clazz)
        majorEditText.setText(major)
        genderRadioGroup.check(genderId)
    }
}
