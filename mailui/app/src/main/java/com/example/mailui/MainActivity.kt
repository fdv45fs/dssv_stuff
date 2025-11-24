package com.example.mailui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        
        val emails = listOf(
            Email(
                senderName = "ABC",
                subject = "XYZ",
                preview = "a123",
                time = "12:34 PM",
                avatarColor = 0
            ),
            Email(
                senderName = "XYZ",
                subject = "ABC ABC ABC",
                preview = "a123 a123 a123...",
                time = "11:22 AM",
                avatarColor = 0
            ),
            Email(
                senderName = "ABC",
                subject = "a123 XYZ ABC",
                preview = "XYZ, ABC, a123, XYZ...",
                time = "11:04 AM",
                avatarColor = 0
            ),
            Email(
                senderName = "a123",
                subject = "ABC XYZ",
                preview = "XYZ ABC - a123...",
                time = "10:26 AM",
                avatarColor = 0
            ),
            Email(
                senderName = "XYZ",
                subject = "ABC a123",
                preview = "ABC a123, XYZ...",
                time = "9:15 AM",
                avatarColor = 0
            )
        )
        
        recyclerView.adapter = EmailAdapter(emails)
    }
}