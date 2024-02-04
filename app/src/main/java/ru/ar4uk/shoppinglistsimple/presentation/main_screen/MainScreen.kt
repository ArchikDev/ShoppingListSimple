package ru.ar4uk.shoppinglistsimple.presentation.main_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ru.ar4uk.shoppinglistsimple.R
import ru.ar4uk.shoppinglistsimple.navigation.NavigationGraph
import ru.ar4uk.shoppinglistsimple.presentation.helpers.dialog.MainDialog
import ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen.ShoppingListViewModel
import ru.ar4uk.shoppinglistsimple.ui.theme.BlueLight

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(
    mainNavHostController: NavHostController,
    viewModel: MainScreenViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNav(navController)
        },
        floatingActionButton = {
            FloatingActionButton(
                shape = CircleShape,
                backgroundColor = BlueLight,
                onClick = {
                    viewModel.onEvent(MainScreenEvent.OnShowDialog)
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.fl_add_icon),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true
    ) {
        NavigationGraph(navController) { route ->
            mainNavHostController.navigate(route)
        }
        MainDialog(dialogController = viewModel)
    }
}