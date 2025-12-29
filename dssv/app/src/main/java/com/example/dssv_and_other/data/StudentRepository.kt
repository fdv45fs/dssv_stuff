package com.example.dssv_and_other.data

import android.content.ContentValues
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dssv_and_other.Student

class StudentRepository(context: Context) {
    private val dbHelper = StudentDbHelper(context)

    private val _students = MutableLiveData<List<Student>>(emptyList())
    val students: LiveData<List<Student>> get() = _students

    init {
        refreshStudents()
    }

    fun addStudent(student: Student) {
        val values = ContentValues().apply {
            put(StudentDbContract.COL_STUDENT_ID, student.studentId)
            put(StudentDbContract.COL_FULL_NAME, student.fullName)
        }

        val db = dbHelper.writableDatabase
        db.insertOrThrow(StudentDbContract.TABLE_STUDENTS, null, values)
        refreshStudents()
    }

    fun updateStudent(originalId: String, updated: Student) {
        val db = dbHelper.writableDatabase

        if (originalId == updated.studentId) {
            val values = ContentValues().apply {
                put(StudentDbContract.COL_FULL_NAME, updated.fullName)
            }
            db.update(
                StudentDbContract.TABLE_STUDENTS,
                values,
                "${StudentDbContract.COL_STUDENT_ID} = ?",
                arrayOf(originalId)
            )
        } else {
            val newValues = ContentValues().apply {
                put(StudentDbContract.COL_STUDENT_ID, updated.studentId)
                put(StudentDbContract.COL_FULL_NAME, updated.fullName)
            }
            db.beginTransaction()
            try {
                db.delete(
                    StudentDbContract.TABLE_STUDENTS,
                    "${StudentDbContract.COL_STUDENT_ID} = ?",
                    arrayOf(originalId)
                )
                db.insertOrThrow(StudentDbContract.TABLE_STUDENTS, null, newValues)
                db.setTransactionSuccessful()
            } finally {
                db.endTransaction()
            }
        }

        refreshStudents()
    }

    fun deleteStudent(studentId: String) {
        val db = dbHelper.writableDatabase
        db.delete(
            StudentDbContract.TABLE_STUDENTS,
            "${StudentDbContract.COL_STUDENT_ID} = ?",
            arrayOf(studentId)
        )
        refreshStudents()
    }

    fun findById(studentId: String): Student? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            StudentDbContract.TABLE_STUDENTS,
            arrayOf(StudentDbContract.COL_STUDENT_ID, StudentDbContract.COL_FULL_NAME),
            "${StudentDbContract.COL_STUDENT_ID} = ?",
            arrayOf(studentId),
            null,
            null,
            null,
            "1"
        )

        cursor.use {
            if (!it.moveToFirst()) return null
            val idIndex = it.getColumnIndexOrThrow(StudentDbContract.COL_STUDENT_ID)
            val nameIndex = it.getColumnIndexOrThrow(StudentDbContract.COL_FULL_NAME)
            return Student(
                studentId = it.getString(idIndex),
                fullName = it.getString(nameIndex)
            )
        }
    }

    private fun refreshStudents() {
        _students.value = getAllStudents()
    }

    private fun getAllStudents(): List<Student> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            StudentDbContract.TABLE_STUDENTS,
            arrayOf(StudentDbContract.COL_STUDENT_ID, StudentDbContract.COL_FULL_NAME),
            null,
            null,
            null,
            null,
            "${StudentDbContract.COL_STUDENT_ID} ASC"
        )

        val result = mutableListOf<Student>()
        cursor.use {
            val idIndex = it.getColumnIndexOrThrow(StudentDbContract.COL_STUDENT_ID)
            val nameIndex = it.getColumnIndexOrThrow(StudentDbContract.COL_FULL_NAME)
            while (it.moveToNext()) {
                result.add(
                    Student(
                        studentId = it.getString(idIndex),
                        fullName = it.getString(nameIndex)
                    )
                )
            }
        }
        return result
    }
}
