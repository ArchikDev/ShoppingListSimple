package ru.ar4uk.shoppinglistsimple.presentation.note_list_screen

import ru.ar4uk.shoppinglistsimple.data.model.ShoppingNoteItem

sealed class NoteListEvent {

    data class OnShowDeleteDialog(val item: ShoppingNoteItem): NoteListEvent()
    data class OnItemClick(val route: String): NoteListEvent()
}