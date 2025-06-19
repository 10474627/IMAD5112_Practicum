package com.example.st10474627_samkelo_matsobane_practicumassignment

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class DetailedViewScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailed_view_screen)

        val tableLayout = findViewById<TableLayout>(R.id.tableLayout)
        val goBackButton = findViewById<Button>(R.id.goBackButton)

        // This is where we add headers
        val headerRow = TableRow(this)
        val headers = listOf("Song Title", "Artist Name", "Rating (1–5)", "Comments")
        for (header in headers) {
            val textView = TextView(this).apply {
                text = header
                setPadding(16, 16, 16, 16)
                textSize = 16f
                setTypeface(null, android.graphics.Typeface.BOLD)
            }
            headerRow.addView(textView)
        }
        tableLayout.addView(headerRow)

        // Adding more details here
        for (entry in MainActivity.itemList) {
            val row = TableRow(this)
            val values = listOf(entry.title, entry.artist, entry.rating, entry.comments)
            for (value in values) {
                val textView = TextView(this).apply {
                    text = value
                    setPadding(16, 16, 16, 16)
                }
                row.addView(textView)
            }
            tableLayout.addView(row)
        }

        goBackButton.setOnClickListener {
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
