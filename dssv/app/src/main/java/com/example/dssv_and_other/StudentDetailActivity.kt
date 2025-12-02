package com.example.dssv_and_other

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class StudentDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val etMssv = findViewById<EditText>(R.id.editTextMssv)
        val etName = findViewById<EditText>(R.id.editTextName)
        val etPhone = findViewById<EditText>(R.id.editTextPhoneNum)
        val etAddress = findViewById<EditText>(R.id.editTextAddress)
        val btnOk = findViewById<Button>(R.id.buttonOk)

//        btnOk.text = "Update"

        val student = intent.getSerializableExtra("STUDENT_DATA") as? Student

        student?.let {
            etMssv.setText(it.studentId)
            etName.setText(it.fullName)
            etPhone.setText(it.phoneNumber)
            etAddress.setText(it.address)
        }

        btnOk.setOnClickListener {
            val updatedStudent = Student(
                etMssv.text.toString(),
                etName.text.toString(),
                etPhone.text.toString(),
                etAddress.text.toString()
            )

            val resultIntent = Intent()
            resultIntent.putExtra("UPDATED_STUDENT", updatedStudent)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}