package com.example.notesapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notesapp.databinding.ActivityMainBinding
import com.example.notesapp.db.FakeDB
import com.example.notesapp.model.Note
import com.example.notesapp.view.adapters.NoteAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        //binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setupNotesRecyclerView()
        setupButtons();

    }

    private fun setupButtons(){
        binding.fabAddNote.setOnClickListener{
            var newNote = Note(
                "",
                ""
            )

            FakeDB.notes.add(0, newNote)
            (binding.rvNotes.adapter as NoteAdapter).reloadData(0)
            (binding.rvNotes.adapter as NoteAdapter).noteCreated()

        }
    }

    private fun setupNotesRecyclerView(){
        binding.rvNotes.adapter = NoteAdapter(FakeDB.notes)
        binding.rvNotes.layoutManager = LinearLayoutManager(this).apply {
            orientation = LinearLayoutManager.VERTICAL
        }
    }
}