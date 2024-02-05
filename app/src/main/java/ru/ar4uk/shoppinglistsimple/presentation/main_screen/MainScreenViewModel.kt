package ru.ar4uk.shoppinglistsimple.presentation.main_screen

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.ar4uk.shoppinglistsimple.data.model.ShoppingListItem
import ru.ar4uk.shoppinglistsimple.data.repository.ShoppingListItemRepo
import ru.ar4uk.shoppinglistsimple.navigation.Routes
import ru.ar4uk.shoppinglistsimple.presentation.helpers.UiEvent
import ru.ar4uk.shoppinglistsimple.presentation.helpers.dialog.DialogController
import ru.ar4uk.shoppinglistsimple.presentation.helpers.dialog.DialogEvent
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val repository: ShoppingListItemRepo
): ViewModel(), DialogController {

    override var dialogTitle = mutableStateOf("List name:")
        private set
    override var editableText = mutableStateOf("")
        private set
    override var openDialog = mutableStateOf(false)
        private set
    override var showEditableText = mutableStateOf(true)
        private set

    private val _uiEvent = Channel<UiEvent>() // передатчик
    val uiEvent = _uiEvent.receiveAsFlow() // приёмник

    var showFloatingButton = mutableStateOf(true)
        private set

    override fun onDialogEvent(event: DialogEvent) {
        when(event) {
            DialogEvent.OnCancel -> {
                openDialog.value = false
                editableText.value = ""
            }
            DialogEvent.OnConfirm -> {
                onEvent(MainScreenEvent.OnItemSave)

                openDialog.value = false
                editableText.value = ""
            }
            is DialogEvent.OnTextChange -> {
                editableText.value = event.text
            }
        }
    }

    fun onEvent(event: MainScreenEvent) {
        when(event) {
            is MainScreenEvent.OnItemSave -> {
                if (editableText.value.isEmpty()) return

                viewModelScope.launch {
                    repository.insertItem(
                        ShoppingListItem(
                            id = null,
                            name = editableText.value,
                            time = "12-12-2023 13:00",
                            allItemsCount = 0,
                            allSelectedItemsCount = 0
                        )
                    )
                }
            }
            is MainScreenEvent.OnShowDialog -> {
                openDialog.value = true
            }

            is MainScreenEvent.Navigate -> {
                sendUiEvent(UiEvent.Navigate(event.route))

                showFloatingButton.value = !(event.route == Routes.ABOUT
                        || event.route == Routes.SETTINGS)
            }
            is MainScreenEvent.NavigateMain -> {
                sendUiEvent(UiEvent.NavigateMain(event.route))
            }
        }
    }

    private fun sendUiEvent(event: UiEvent) {
        viewModelScope.launch {
            _uiEvent.send(event)
        }
    }


}