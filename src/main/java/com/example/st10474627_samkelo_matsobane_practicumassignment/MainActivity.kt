package com.example.st10474627_samkelo_matsobane_practicumassignment
//st10474627_samkelo_matsobane

import android.os.Bundle
import android.widget.*
import android.content.Intent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    companion object {
        val itemList = mutableListOf<SongEntry>()
    }

    data class SongEntry(
        val title: String,
        val artist: String,
        val rating: String,
        val comments: String
    )

    override fun onResume() {
        super.onResume()
        findViewById<EditText>(R.id.songTitle).text.clear()
        findViewById<EditText>(R.id.artistName).text.clear()
        findViewById<EditText>(R.id.rating).text.clear()
        findViewById<EditText>(R.id.comments).text.clear()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val titleEdit = findViewById<EditText>(R.id.songTitle)
        val artistEdit = findViewById<EditText>(R.id.artistName)
        val ratingEdit = findViewById<EditText>(R.id.rating)
        val commentsEdit = findViewById<EditText>(R.id.comments)
        val addToListButton = findViewById<Button>(R.id.addToList)
        val showButton = findViewById<Button>(R.id.showButton)
        val exitButton = findViewById<Button>(R.id.exitButton)

        addToListButton.setOnClickListener {
            val title = titleEdit.text.toString().trim()
            val artist = artistEdit.text.toString().trim()
            val rating = ratingEdit.text.toString().trim()
            val comment = commentsEdit.text.toString().trim()

            if (title.isEmpty() || artist.isEmpty() || rating.isEmpty() || comment.isEmpty()) {
                Snackbar.make(findViewById(R.id.main), "Please fill in all fields", Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val entry = SongEntry(title, artist, rating, comment)
            itemList.add(entry)

            Snackbar.make(findViewById(R.id.main), "Song added to playlist", Snackbar.LENGTH_LONG)
                .setAction("UNDO") {
                    itemList.remove(entry)
                    Snackbar.make(findViewById(R.id.main), "Song removed", Snackbar.LENGTH_SHORT).show()
                }.show()

            val intent = Intent(this, DetailedViewScreen::class.java)
            startActivity(intent)
        }

        showButton.setOnClickListener {
            val intent = Intent(this, DetailedViewScreen::class.java)
            startActivity(intent)
        }

        exitButton.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("Exit App")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes") { _, _ ->
                    finishAffinity()
                }
                .setNegativeButton("Cancel", null)
                .show()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
