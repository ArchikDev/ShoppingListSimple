package ru.ar4uk.shoppinglistsimple.presentation.main_screen

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import ru.ar4uk.shoppinglistsimple.ui.theme.BlueLight
import ru.ar4uk.shoppinglistsimple.ui.theme.GrayLight

@Composable
fun BottomNav(navController: NavHostController) {
    val listNav = listOf(
        BottomNavItem.ListItem,
        BottomNavItem.NoteItem,
        BottomNavItem.AboutItem,
        BottomNavItem.SettingsItem
    )

    BottomNavigation(backgroundColor = Color.White) {
        listNav.forEach { bottomItem ->
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRow = navBackStackEntry?.destination?.route

            BottomNavigationItem(
                selected = currentRow == bottomItem.route,
                onClick = {
                    navController.navigate(bottomItem.route)
                },
                icon = {
                    Icon(painter = painterResource(id = bottomItem.iconId), contentDescription = "icon")
                },
                label = {
                    Text(text = bottomItem.title)
                },
                selectedContentColor = BlueLight,
                unselectedContentColor = GrayLight,
                alwaysShowLabel = false
            )
        }
    }
}