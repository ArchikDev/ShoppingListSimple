package ru.ar4uk.shoppinglistsimple.presentation.main_screen

import ru.ar4uk.shoppinglistsimple.R
import ru.ar4uk.shoppinglistsimple.navigation.Routes

sealed class BottomNavItem(val title: String, val iconId: Int, val route: String) {
    data object ListItem: BottomNavItem("List", R.drawable.nav_list_item_icon, Routes.SHOPPING_LIST)
    data object NoteItem: BottomNavItem("Note", R.drawable.nav_note_item_icon, Routes.NOTE_LIST)
    data object AboutItem: BottomNavItem("About", R.drawable.fl_add_icon, Routes.ABOUT)
    data object SettingsItem: BottomNavItem("Settings", R.drawable.nav_setting_item_icon,  Routes.SETTINGS)
}