package com.example.dssv_and_other.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dssv_and_other.Student
import com.example.dssv_and_other.data.StudentRepository

class StudentViewModel : ViewModel() {
    private val repository = StudentRepository()

    val students: LiveData<List<Student>> = repository.students

    val studentIdInput = MutableLiveData<String>()
    val fullNameInput = MutableLiveData<String>()

    val studentIdError = MutableLiveData<String?>()
    val fullNameError = MutableLiveData<String?>()

    private var originalStudentId: String? = null

    private val _messageEvent = MutableLiveData<Event<String>>()
    val messageEvent: LiveData<Event<String>> = _messageEvent

    private val _navigateBackEvent = MutableLiveData<Event<Unit>>()
    val navigateBackEvent: LiveData<Event<Unit>> = _navigateBackEvent

    fun startAdd() {
        originalStudentId = null
        studentIdInput.value = ""
        fullNameInput.value = ""
        clearErrors()
    }

    fun startEdit(studentId: String?) {
        clearErrors()
        if (studentId == null) {
            _messageEvent.value = Event("Invalid student id")
            _navigateBackEvent.value = Event(Unit)
            return
        }
        val student = repository.findById(studentId)
        if (student == null) {
            _messageEvent.value = Event("Student not found")
            _navigateBackEvent.value = Event(Unit)
            return
        }
        originalStudentId = student.studentId
        studentIdInput.value = student.studentId
        fullNameInput.value = student.fullName
    }

    fun save() {
        clearErrors()
        val id = studentIdInput.value?.trim() ?: ""
        val name = fullNameInput.value?.trim() ?: ""

        var hasError = false
        if (id.isEmpty()) {
            studentIdError.value = "Mã không được để trống"
            hasError = true
        }
        if (name.isEmpty()) {
            fullNameError.value = "Họ tên không được để trống"
            hasError = true
        }
        if (hasError) return

        val existing = repository.findById(id)
        if (originalStudentId == null) {
            if (existing != null) {
                studentIdError.value = "Mã đã tồn tại"
                return
            }
            repository.addStudent(Student(id, name))
            _messageEvent.value = Event("Thêm sinh viên thành công")
            _navigateBackEvent.value = Event(Unit)
        } else {
            if (id != originalStudentId && existing != null) {
                studentIdError.value = "Mã đã tồn tại"
                return
            }
            repository.updateStudent(originalStudentId!!, Student(id, name))
            _messageEvent.value = Event("Cập nhật thành công")
            _navigateBackEvent.value = Event(Unit)
        }
    }

    fun deleteStudent(studentId: String) {
        repository.deleteStudent(studentId)
    }

    private fun clearErrors() {
        studentIdError.value = null
        fullNameError.value = null
    }
}