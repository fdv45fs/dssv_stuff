package com.example.dssv_and_other

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var etStudentId: EditText
    private lateinit var etName: EditText
    private lateinit var btnAdd: Button
    private lateinit var btnUpdate: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private val students = mutableListOf<Student>()
    private var selectedStudent: Student? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        etStudentId = findViewById(R.id.etStudentId)
        etName = findViewById(R.id.etName)
        btnAdd = findViewById(R.id.btnAdd)
        btnUpdate = findViewById(R.id.btnUpdate)
        recyclerView = findViewById(R.id.recyclerView)

        adapter = StudentAdapter(students,
            onEditClick = { student ->
                selectedStudent = student
                etStudentId.setText(student.studentId)
                etName.setText(student.fullName)
            },
            onDeleteClick = { student ->
                val position = students.indexOf(student)
                if (position != -1) {
                    students.removeAt(position)
                    adapter.notifyItemRemoved(position)
                    if (selectedStudent == student) {
                        selectedStudent = null
                        etStudentId.text.clear()
                        etName.text.clear()
                    }
                }
            }
        )

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        btnAdd.setOnClickListener {
            val id = etStudentId.text.toString()
            val name = etName.text.toString()
            if (id.isNotEmpty() && name.isNotEmpty()) {
                val newStudent = Student(id, name)
                students.add(newStudent)
                adapter.notifyItemInserted(students.size - 1)
                etStudentId.text.clear()
                etName.text.clear()
                selectedStudent = null
            }
        }

        btnUpdate.setOnClickListener {
            if (selectedStudent != null) {
                val id = etStudentId.text.toString()
                val name = etName.text.toString()
                if (id.isNotEmpty() && name.isNotEmpty()) {
                    selectedStudent?.studentId = id
                    selectedStudent?.fullName = name
                    adapter.notifyDataSetChanged()
                    etStudentId.text.clear()
                    etName.text.clear()
                    selectedStudent = null
                }
            }
        }
    }
}