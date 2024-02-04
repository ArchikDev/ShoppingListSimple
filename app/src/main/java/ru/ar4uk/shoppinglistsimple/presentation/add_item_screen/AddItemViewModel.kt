package ru.ar4uk.shoppinglistsimple.presentation.add_item_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingItem
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingListItem
import ru.ar4uk.shoppinglistsimple.data.repository.ShoppingItemRepo
import ru.ar4uk.shoppinglistsimple.presentation.helpers.dialog.DialogController
import ru.ar4uk.shoppinglistsimple.presentation.helpers.dialog.DialogEvent
import ru.ar4uk.shoppinglistsimple.presentation.main_screen.MainScreenEvent
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val repository: ShoppingItemRepo,
    savedStateHandle: SavedStateHandle
): ViewModel(), DialogController {

    var itemsList: Flow<List<ShoppingItem>>? = null

    init {
        val listId = savedStateHandle.get<String>("listId")?.toInt()

        itemsList = listId?.let { repository.allItemsById(it) }
    }

    override var dialogTitle = mutableStateOf("")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(false)
        private set

    override fun onDialogEvent(event: DialogEvent) {
        when(event) {
            DialogEvent.OnCancel -> {
                openDialog.value = false
                editableText.value = ""
            }
            DialogEvent.OnConfirm -> {
                openDialog.value = false
                editableText.value = ""
            }
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
        }
    }

    fun onEvent(event: AddItemEvent) {
        when(event) {

//            is MainScreenEvent.OnItemSave -> {
////                if (editableText.value.isEmpty()) return
////
////                viewModelScope.launch {
////                    repository.insertItem(
////                        ShoppingListItem(
////                            id = null,
////                            name = editableText.value,
////                            time = "12-12-2023 13:00",
////                            allItemsCount = 0,
////                            allSelectedItemsCount = 0
////                        )
////                    )
////                }
//            }
//            is MainScreenEvent.OnShowDialog -> {
//                openDialog.value = true
//            }
            is AddItemEvent.OnCheckedChange -> TODO()
            is AddItemEvent.OnDeleteDialog -> TODO()
            AddItemEvent.OnItemSave -> TODO()
            is AddItemEvent.OnShowEditDialog -> TODO()
            is AddItemEvent.OnTextChange -> TODO()
        }
    }
}