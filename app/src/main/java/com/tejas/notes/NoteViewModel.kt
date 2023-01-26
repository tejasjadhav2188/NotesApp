package com.tejas.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) : AndroidViewModel(application){

    val getAllNotes : LiveData<List<Note>>
    val repository : NoteRepository
    init{
        val dao = NoteDatabase.getDatabase(application).getnoteDao()
        repository = NoteRepository(dao)
        getAllNotes = repository.allNotes
    }

    fun deletenote (note : Note) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(note)
    }

    fun insertnote (note : Note) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(note)
    }

}