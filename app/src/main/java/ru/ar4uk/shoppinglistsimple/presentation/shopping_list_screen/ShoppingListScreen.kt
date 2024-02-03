package ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import ru.ar4uk.shoppinglistsimple.presentation.helpers.dialog.MainDialog

@Composable
fun ShoppingListScreen(
    viewModel: ShoppingListViewModel = hiltViewModel()
) {
//    UIShoppingListItem()
    MainDialog(dialogController = viewModel)
}