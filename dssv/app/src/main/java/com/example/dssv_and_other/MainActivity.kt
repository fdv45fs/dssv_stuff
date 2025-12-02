package com.example.dssv_and_other

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter
    private val students = mutableListOf<Student>()
    private var selectedPosition: Int = -1

    private val addStudentLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val newStudent = result.data?.getSerializableExtra("NEW_STUDENT") as? Student
            if (newStudent != null) {
                students.add(newStudent)
                adapter.notifyItemInserted(students.size - 1)
            }
        }
    }

    private val updateStudentLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val updatedStudent = result.data?.getSerializableExtra("UPDATED_STUDENT") as? Student
            if (updatedStudent != null && selectedPosition != -1) {
                students[selectedPosition] = updatedStudent
                adapter.notifyItemChanged(selectedPosition)
                selectedPosition = -1
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = StudentAdapter(students) { student ->
            // Sự kiện click vào 1 dòng -> Mở DetailActivity
            selectedPosition = students.indexOf(student)
            val intent = Intent(this, StudentDetailActivity::class.java)
            intent.putExtra("STUDENT_DATA", student)
            updateStudentLauncher.launch(intent)
        }
        recyclerView.adapter = adapter
    }

    // Khởi tạo menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_add -> {
                val intent = Intent(this, AddStudentActivity::class.java)
                addStudentLauncher.launch(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}