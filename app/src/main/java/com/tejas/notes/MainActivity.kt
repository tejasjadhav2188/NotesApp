package com.tejas.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INoteOnclicked {

    private lateinit var viewModel: NoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = NotesRVAdapter(this,this)
        recyclerView.adapter = adapter

        viewModel = ViewModelProvider(this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)).get(NoteViewModel::class.java)
        viewModel.getAllNotes.observe(this, Observer {list ->
            list ?.let {
                adapter.updatelist(it)
            }

        })
    }

    override fun onItemClicked(note: Note) {
        viewModel.deletenote(note)
        Toast.makeText(this,"${note.note} Deleted",Toast.LENGTH_SHORT).show()
    }

    fun saveNote(view: View) {
        val note = EntryText.text.toString()
        if(note.isNotEmpty()){
            viewModel.insertnote(Note(note))
            Toast.makeText(this,"${Note(note).note } Inserted ", Toast.LENGTH_SHORT).show()
        }
    }
}