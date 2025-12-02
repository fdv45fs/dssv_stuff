package com.example.dssv_and_other

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddStudentActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form)

        val etMssv = findViewById<EditText>(R.id.editTextMssv)
        val etName = findViewById<EditText>(R.id.editTextName)
        val etPhone = findViewById<EditText>(R.id.editTextPhoneNum)
        val etAddress = findViewById<EditText>(R.id.editTextAddress)
        val btnOk = findViewById<Button>(R.id.buttonOk)

//        etMssv.setText("")
//        etName.setText("")
//        etPhone.setText("")
//        etAddress.setText("")

        btnOk.setOnClickListener {
            val id = etMssv.text.toString()
            val name = etName.text.toString()
            val phone = etPhone.text.toString()
            val address = etAddress.text.toString()

            if (id.isNotEmpty() && name.isNotEmpty()) {
                val newStudent = Student(id, name, phone, address)
                val intent = Intent()
                intent.putExtra("NEW_STUDENT", newStudent)
                setResult(Activity.RESULT_OK, intent)
                finish() // Đóng activity này để quay về Main
            }
        }
    }
}