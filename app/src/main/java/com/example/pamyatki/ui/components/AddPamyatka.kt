package com.example.pamyatki.ui.components

import androidx.compose.runtime.mutableStateListOf

class AddPamyatka {
    private var _notes = mutableStateListOf<Note>()
    private var id = 1
    val notes: List<Note> get() = _notes
    fun addNote(theme: String, text: String){
        _notes.add(Note(id = id++, theme = theme, text = text))
    }
    fun delNote(note: Note){
        _notes.remove(note)
    }
}

data class Note(
    val id: Int,
    val theme: String,
    val text: String
)