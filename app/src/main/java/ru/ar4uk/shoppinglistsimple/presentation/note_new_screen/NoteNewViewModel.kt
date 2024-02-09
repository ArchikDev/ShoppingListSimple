package ru.ar4uk.shoppinglistsimple.presentation.note_new_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingNoteItem
import ru.ar4uk.shoppinglistsimple.data.repository.ShoppingNoteItemRepo
import ru.ar4uk.shoppinglistsimple.presentation.add_item_screen.AddItemEvent
import ru.ar4uk.shoppinglistsimple.presentation.helpers.UiEvent
import javax.inject.Inject

@HiltViewModel
class NoteNewViewModel @Inject constructor(
    private val repository: ShoppingNoteItemRepo,
    savedStateHandle: SavedStateHandle
): ViewModel() {

    private var noteId = -1
    private var noteItem: ShoppingNoteItem? = null

    private val _uiEvent = Channel<UiEvent>() // передатчик
    val uiEvent = _uiEvent.receiveAsFlow() // приёмник

    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    init {
        noteId = savedStateHandle.get<String>("noteId")?.toInt() ?: -1

        if (noteId != -1) {
            viewModelScope.launch {
                repository.noteById(noteId).let { noteItem ->
                    title = noteItem.title
                    description = noteItem.description

                    this@NoteNewViewModel.noteItem = noteItem
                }
            }
        }
    }

    fun onEvent(event: NewNoteEvent) {
        when(event) {
            is NewNoteEvent.OnDescriptionChange -> {
                description = event.description
            }
            is NewNoteEvent.OnTitleChange -> {
                title = event.title
            }
            NewNoteEvent.OnSave -> {
                viewModelScope.launch {
                    if (title.isEmpty()) {
                        sendUiEvent(UiEvent.ShowSnackBar("Title can not be empty"))

                        return@launch
                    }
                    repository.insertItem(
                        ShoppingNoteItem(
                            noteItem?.id,
                            title,
                            description,
                            "12/12/2023 13:00"
                        )
                    )
                    sendUiEvent(UiEvent.PopBackStack)
                }
            }
        }
    }
    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}