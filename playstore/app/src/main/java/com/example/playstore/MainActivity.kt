package com.example.playstore

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        
        // Setup RecyclerView
        val mainRecyclerView = findViewById<RecyclerView>(R.id.mainRecyclerView)
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        
        // Create dummy data
        val categories = createDummyData()
        
        // Set adapter
        mainRecyclerView.adapter = CategoryAdapter(categories)
    }
    
    private fun createDummyData(): List<Category> {
        return listOf(
            Category(
                title = "ABC Category",
                apps = listOf(
                    App("ABC App 1", "XYZ description", "a123 MB"),
                    App("ABC App 2", "XYZ info", "b456 MB"),
                    App("ABC App 3", "XYZ details", "c789 MB"),
                    App("ABC App 4", "XYZ content", "a111 MB"),
                    App("ABC App 5", "XYZ text", "b222 MB")
                )
            ),
            Category(
                title = "ABC Category",
                apps = listOf(
                    App("ABC App 1", "XYZ description", "a123 MB"),
                    App("ABC App 2", "XYZ info", "b456 MB"),
                    App("ABC App 3", "XYZ details", "c789 MB"),
                    App("ABC App 4", "XYZ content", "a111 MB"),
                    App("ABC App 5", "XYZ text", "b222 MB")
                )
            ),
            Category(
                title = "ABC Category",
                apps = listOf(
                    App("ABC App 1", "XYZ description", "a123 MB"),
                    App("ABC App 2", "XYZ info", "b456 MB"),
                    App("ABC App 3", "XYZ details", "c789 MB"),
                    App("ABC App 4", "XYZ content", "a111 MB"),
                    App("ABC App 5", "XYZ text", "b222 MB")
                )
            ),
            Category(
                title = "ABC Category",
                apps = listOf(
                    App("ABC App 1", "XYZ description", "a123 MB"),
                    App("ABC App 2", "XYZ info", "b456 MB"),
                    App("ABC App 3", "XYZ details", "c789 MB"),
                    App("ABC App 4", "XYZ content", "a111 MB"),
                    App("ABC App 5", "XYZ text", "b222 MB")
                )
            ),
            Category(
                title = "ABC Category",
                apps = listOf(
                    App("ABC App 1", "XYZ description", "a123 MB"),
                    App("ABC App 2", "XYZ info", "b456 MB"),
                    App("ABC App 3", "XYZ details", "c789 MB"),
                    App("ABC App 4", "XYZ content", "a111 MB"),
                    App("ABC App 5", "XYZ text", "b222 MB")
                )
            ),
            Category(
                title = "ABC Category",
                apps = listOf(
                    App("ABC App 1", "XYZ description", "a123 MB"),
                    App("ABC App 2", "XYZ info", "b456 MB"),
                    App("ABC App 3", "XYZ details", "c789 MB"),
                    App("ABC App 4", "XYZ content", "a111 MB"),
                    App("ABC App 5", "XYZ text", "b222 MB")
                )
            ),
            Category(
                title = "ABC Category",
                apps = listOf(
                    App("ABC App 1", "XYZ description", "a123 MB"),
                    App("ABC App 2", "XYZ info", "b456 MB"),
                    App("ABC App 3", "XYZ details", "c789 MB"),
                    App("ABC App 4", "XYZ content", "a111 MB"),
                    App("ABC App 5", "XYZ text", "b222 MB")
                )
            )
        )
    }
}