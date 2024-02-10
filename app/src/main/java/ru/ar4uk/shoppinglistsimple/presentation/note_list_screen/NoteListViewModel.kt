package ru.ar4uk.shoppinglistsimple.presentation.note_list_screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingNoteItem
import ru.ar4uk.shoppinglistsimple.data.repository.ShoppingNoteItemRepo
import ru.ar4uk.shoppinglistsimple.datastore.DataStoreManager
import ru.ar4uk.shoppinglistsimple.presentation.helpers.UiEvent
import ru.ar4uk.shoppinglistsimple.presentation.helpers.dialog.DialogController
import ru.ar4uk.shoppinglistsimple.presentation.helpers.dialog.DialogEvent
import ru.ar4uk.shoppinglistsimple.presentation.settings_screen.ColorUtils
import ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen.ShoppingListEvent
import javax.inject.Inject

@HiltViewModel
class NoteListViewModel @Inject constructor(
    private val repository: ShoppingNoteItemRepo,
    private val dataStoreManager: DataStoreManager
): ViewModel(), DialogController {

    val noteListFlow = repository.allNotes()
    private var noteItem: ShoppingNoteItem? = null
    var titleColor = mutableStateOf(ColorUtils.colorList[0])

    var noteList by mutableStateOf(listOf<ShoppingNoteItem>())
    var originalNoteList = listOf<ShoppingNoteItem>()

    private val _uiEvent = Channel<UiEvent>() // передатчик
    val uiEvent = _uiEvent.receiveAsFlow() // приёмник
    var searchText by mutableStateOf("")
        private set

    override var dialogTitle = mutableStateOf("Delete?")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            dataStoreManager.getStringPreference(
                DataStoreManager.TITLE_COLOR,
                ColorUtils.colorList[0]
            ).collect { color ->
                titleColor.value = color
            }
        }

        viewModelScope.launch {
            noteListFlow.collect { list ->
                noteList = list
                originalNoteList = list
            }
        }
    }

    fun onEvent(event: NoteListEvent) {
        when(event) {
            is NoteListEvent.OnItemClick -> {
                sendUiEvent(UiEvent.Navigate(event.route))
            }
            is NoteListEvent.OnShowDeleteDialog -> {
                openDialog.value = true
                noteItem = event.item
            }
            is NoteListEvent.UnDoneDeleteItem -> {
                viewModelScope.launch {
                    noteItem?.let { repository.insertItem(it) }
                }
            }

            is NoteListEvent.OnTextSearchChange -> {
                searchText = event.text
                noteList = originalNoteList.filter { note ->
                    note.title.lowercase().startsWith(searchText.lowercase())
                }
            }
        }
    }

    override fun onDialogEvent(event: DialogEvent) {

        when(event) {
            DialogEvent.OnCancel -> {
                openDialog.value = false
            }
            DialogEvent.OnConfirm -> {
                viewModelScope.launch {
                    noteItem?.let { repository.deleteItem(it) }

                    sendUiEvent(UiEvent.ShowSnackBar("Undone delete item?"))
                }

                openDialog.value = false

            }

            else -> {}
        }

    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }
}