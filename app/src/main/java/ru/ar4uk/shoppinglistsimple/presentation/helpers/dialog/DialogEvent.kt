package ru.ar4uk.shoppinglistsimple.presentation.helpers.dialog

sealed class DialogEvent {
    data class OnTextChange(val text: String): DialogEvent()
    object OnConfirm: DialogEvent()
    object OnCancel : DialogEvent()
}