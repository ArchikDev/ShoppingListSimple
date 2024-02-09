package ru.ar4uk.shoppinglistsimple.presentation.settings_screen

sealed class SettingsEvent {
    data class OnItemSelected(val color: String): SettingsEvent()
}