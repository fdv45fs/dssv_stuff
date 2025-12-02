package com.example.dssv_and_other

import java.io.Serializable

data class Student(
    var studentId: String,
    var fullName: String,
    var phoneNumber: String,
    var address: String
) : Serializable
