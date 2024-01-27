package ru.ar4uk.shoppinglistsimple.presentation.main_screen

import ru.ar4uk.shoppinglistsimple.R

sealed class BottomNavItem(val title: String, val iconId: Int, val route: String) {
    data object ListItem: BottomNavItem("List", R.drawable.nav_list_item_icon, "route1")
    data object NoteItem: BottomNavItem("Note", R.drawable.nav_note_item_icon, "route1")
    data object AboutItem: BottomNavItem("About", R.drawable.fl_add_icon, "route1")
    data object SettingsItem: BottomNavItem("Settings", R.drawable.nav_setting_item_icon, "route1")
}