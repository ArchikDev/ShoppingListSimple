package ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingListItem
import ru.ar4uk.shoppinglistsimple.data.repository.ShoppingListItemRepo
import ru.ar4uk.shoppinglistsimple.presentation.helpers.UiEvent
import ru.ar4uk.shoppinglistsimple.presentation.helpers.dialog.DialogController
import ru.ar4uk.shoppinglistsimple.presentation.helpers.dialog.DialogEvent
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val repository: ShoppingListItemRepo
): ViewModel(), DialogController {

    private val list = repository.allItems()

    private val _uiEvent = Channel<UiEvent>() // передатчик
    val uiEvent = _uiEvent.receiveAsFlow() // приёмник

    private var listItem: ShoppingListItem? = null

    override var dialogTitle = mutableStateOf("")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(false)
        private set

    fun onEvent(event: ShoppingListEvent) {
        when(event) {
            is ShoppingListEvent.OnItemSave -> {
                viewModelScope.launch {
                    repository.insertItem(
                        ShoppingListItem(
                            id = listItem?.id,
                            name = editableText.value,
                            time = "12-12-2023 13:00",
                            allItemsCount = listItem?.allItemsCount ?: 0,
                            allSelectedItemsCount = listItem?.allSelectedItemsCount ?: 0
                        )
                    )
                }
            }
            is ShoppingListEvent.OnItemClick -> {
//                event.route
                sendUiEvent(UiEvent.Navigate(event.route))
            }
            is ShoppingListEvent.OnShowEditDialog -> {
                listItem = event.item

                openDialog.value = true
                editableText.value = listItem?.name ?: ""
                dialogTitle.value = "List name"
                showEditableText.value = true
            }
            is ShoppingListEvent.OnShowDeleteDialog -> {
                listItem = event.item

                openDialog.value = true
                dialogTitle.value = "Delete this item?"
                showEditableText.value = false

            }
        }
    }

    override fun onDialogEvent(event: DialogEvent) {

        when(event) {
            DialogEvent.OnCancel -> {
                openDialog.value = false
            }
            DialogEvent.OnConfirm -> {
                if (showEditableText.value) {
                    onEvent(ShoppingListEvent.OnItemSave)
                } else {
                    viewModelScope.launch {
                        listItem?.let { repository.deleteItem(it) }
                    }
                }

                openDialog.value = false
            }
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
        }

    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }

}