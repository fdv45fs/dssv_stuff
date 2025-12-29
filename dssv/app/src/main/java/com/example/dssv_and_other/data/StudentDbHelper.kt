package com.example.dssv_and_other.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

internal object StudentDbContract {
    const val DB_NAME = "students.db"
    const val DB_VERSION = 1

    const val TABLE_STUDENTS = "students"

    const val COL_STUDENT_ID = "student_id"
    const val COL_FULL_NAME = "full_name"

    val SQL_CREATE_TABLE = """
        CREATE TABLE $TABLE_STUDENTS (
            $COL_STUDENT_ID TEXT PRIMARY KEY,
            $COL_FULL_NAME TEXT NOT NULL
        )
    """.trimIndent()
}

class StudentDbHelper(context: Context) : SQLiteOpenHelper(
    context.applicationContext,
    StudentDbContract.DB_NAME,
    null,
    StudentDbContract.DB_VERSION
) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(StudentDbContract.SQL_CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS ${StudentDbContract.TABLE_STUDENTS}")
        onCreate(db)
    }
}
