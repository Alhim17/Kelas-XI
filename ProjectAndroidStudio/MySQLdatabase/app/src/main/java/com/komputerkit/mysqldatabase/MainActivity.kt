package com.komputerkit.mysqldatabase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var dbHelper: DatabaseHelper
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dbHelper = DatabaseHelper(this)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Cek jika database kosong, tambahkan data statis
        if (dbHelper.readAllStudents().isEmpty()) {
            dbHelper.insertStudent(Student(1, "Budi", "XII IPA 1"))
            dbHelper.insertStudent(Student(2, "Siti", "XII IPA 2"))
            dbHelper.insertStudent(Student(3, "Andi", "XI IPS 1"))
        }

        val students = dbHelper.readAllStudents()
        adapter = StudentAdapter(students)
        recyclerView.adapter = adapter
    }
}
