package com.komputerkit.mysqldatabase

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor

// Data class untuk model siswa
data class Student(val id: Int, val name: String, val studentClass: String)

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        const val DATABASE_NAME = "Student.db"
        const val DATABASE_VERSION = 1
        const val TABLE_NAME = "students"
        const val COL_ID = "id"
        const val COL_NAME = "name"
        const val COL_CLASS = "class"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTable = "CREATE TABLE $TABLE_NAME (" +
                "$COL_ID INTEGER PRIMARY KEY, " +
                "$COL_NAME TEXT, " +
                "$COL_CLASS TEXT)"
        db.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    // Fungsi untuk membaca semua data siswa
    fun readAllStudents(): List<Student> {
        val students = mutableListOf<Student>()
        val db = this.readableDatabase
        var cursor: Cursor? = null
        try {
            cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
            if (cursor.moveToFirst()) {
                do {
                    val id = cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID))
                    val name = cursor.getString(cursor.getColumnIndexOrThrow(COL_NAME))
                    val studentClass = cursor.getString(cursor.getColumnIndexOrThrow(COL_CLASS))
                    students.add(Student(id, name, studentClass))
                } while (cursor.moveToNext())
            }
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            cursor?.close()
            db.close()
        }
        return students
    }

    // Fungsi untuk menambah data siswa (untuk data statis awal)
    fun insertStudent(student: Student): Boolean {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COL_ID, student.id)
            put(COL_NAME, student.name)
            put(COL_CLASS, student.studentClass)
        }
        val result = db.insert(TABLE_NAME, null, values)
        db.close()
        return result != -1L
    }
}
