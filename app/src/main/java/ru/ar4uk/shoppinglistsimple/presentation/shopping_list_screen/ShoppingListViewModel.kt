package ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingListItem
import ru.ar4uk.shoppinglistsimple.data.repository.ShoppingListItemRepo
import javax.inject.Inject

@HiltViewModel
class ShoppingListViewModel @Inject constructor(
    private val repository: ShoppingListItemRepo
): ViewModel() {

    private var listItem: ShoppingListItem? = null

    fun onEvent(event: ShoppingListEvent) {
        when(event) {
            is ShoppingListEvent.OnItemSave -> {
                viewModelScope.launch {
                    repository.insertItem(
                        ShoppingListItem(
                            id = listItem?.id,
                            name = "List 1",
                            time = "12-12-2023 13:00",
                            allItemsCount = listItem?.allItemsCount ?: 0,
                            allSelectedItemsCount = listItem?.allSelectedItemsCount ?: 0
                        )
                    )
                }
            }
            is ShoppingListEvent.OnItemClick -> {

            }
            is ShoppingListEvent.OnShowEditDialog -> {
//                listItem = event.item
            }
            is ShoppingListEvent.OnShowDeleteDialog -> {

            }
        }
    }

}