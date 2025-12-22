package com.example.dssv_and_other.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dssv_and_other.Student

class StudentRepository {
    private val studentList = mutableListOf<Student>()
    private val _students = MutableLiveData<List<Student>>(studentList.toList())
    val students: LiveData<List<Student>> get() = _students

    fun addStudent(student: Student) {
        studentList.add(student)
        _students.value = studentList.toList()
    }

    fun updateStudent(originalId: String, updated: Student) {
        val index = studentList.indexOfFirst { it.studentId == originalId }
        if (index != -1) {
            studentList[index] = updated
            _students.value = studentList.toList()
        }
    }

    fun deleteStudent(studentId: String) {
        val index = studentList.indexOfFirst { it.studentId == studentId }
        if (index != -1) {
            studentList.removeAt(index)
            _students.value = studentList.toList()
        }
    }

    fun findById(studentId: String): Student? = studentList.find { it.studentId == studentId }
}