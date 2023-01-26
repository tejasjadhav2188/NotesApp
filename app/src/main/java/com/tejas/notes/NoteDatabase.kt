package com.tejas.notes

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlin.synchronized

@Database(entities = arrayOf(Note::class), version = 1, exportSchema = false)
public abstract class NoteDatabase :RoomDatabase() {

    abstract fun getnoteDao() : NoteDao

    companion object{
        @Volatile
        private var INSTANCE : NoteDatabase? = null

        fun getDatabase(context : Context):NoteDatabase{

            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_database"
                ).build()
                INSTANCE =  instance
                instance
            }
        }
    }


}