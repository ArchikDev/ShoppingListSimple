package ru.ar4uk.shoppinglistsimple.presentation.add_item_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingItem
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingListItem
import ru.ar4uk.shoppinglistsimple.data.repository.ShoppingItemRepo
import ru.ar4uk.shoppinglistsimple.presentation.helpers.dialog.DialogController
import ru.ar4uk.shoppinglistsimple.presentation.helpers.dialog.DialogEvent
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    private val repository: ShoppingItemRepo,
    savedStateHandle: SavedStateHandle
): ViewModel(), DialogController {

    var itemsList: Flow<List<ShoppingItem>>? = null
    var addItem: ShoppingItem? = null
    var listId: Int = -1
    var shoppingListItem: ShoppingListItem? = null

    init {
        listId = savedStateHandle.get<String>("listId")?.toInt()!!

        itemsList = repository.allItemsById(listId)

        viewModelScope.launch {
            shoppingListItem = repository.listItem(listId)
        }
    }

    var itemText = mutableStateOf("")
        private set

    override var dialogTitle = mutableStateOf("Edit name")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(true)
        private set

    override fun onDialogEvent(event: DialogEvent) {
        when(event) {
            DialogEvent.OnCancel -> {
                openDialog.value = false
                editableText.value = ""
            }
            DialogEvent.OnConfirm -> {
                openDialog.value = false
                itemText.value = editableText.value
                editableText.value = ""
            }
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
        }
    }

    fun onEvent(event: AddItemEvent) {
        when(event) {
            is AddItemEvent.OnCheckedChange -> {
                viewModelScope.launch {
                    repository.insertItem(event.item)
                }
                updateShoppingListCount()
            }
            is AddItemEvent.OnDelete -> {
                viewModelScope.launch {
                    repository.deleteItem(event.item)
                }
                updateShoppingListCount()
            }
            AddItemEvent.OnItemSave -> {
                viewModelScope.launch {
                    if (listId == -1) return@launch

                    repository.insertItem(
                        ShoppingItem(
                            id = addItem?.id,
                            name = itemText.value,
                            isCheck = addItem?.isCheck ?: false,
                            shoppingListItemID = listId
                        )
                    )
                    itemText.value = ""
                    addItem = null
                }

                updateShoppingListCount()
            }
            is AddItemEvent.OnShowEditDialog -> {
                addItem = event.item
                openDialog.value = true
                editableText.value = addItem?.name ?: ""
            }
            is AddItemEvent.OnTextChange -> {
                itemText.value = event.text
            }
        }
    }

    private fun updateShoppingListCount() {
        viewModelScope.launch {
            itemsList?.collect { list ->
                var counter = 0

                list.forEach { item ->
                    if (item.isCheck) counter++
                }

                shoppingListItem?.copy(
                    allItemsCount = list.size,
                    allSelectedItemsCount = counter
                )?.let {
                    repository.insertListItem(
                        it
                    )
                }
            }
        }
    }

}