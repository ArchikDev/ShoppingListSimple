package ru.ar4uk.shoppinglistsimple.presentation.helpers

sealed class UiEvent {
    object PopBackStack: UiEvent()
    data class Navigate(val route: String): UiEvent()
    data class ShowSnackBar(val message: String): UiEvent()
}