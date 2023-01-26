package com.tejas.notes

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(note:Note)

    @Delete
    suspend fun delete(note : Note)

    @Query("SELECT * FROM note_table ORDER BY note")
    fun fetchAll() : LiveData<List<Note>>
}