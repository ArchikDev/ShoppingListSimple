package ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.ar4uk.shoppinglistsimple.presentation.helpers.UiEvent
import ru.ar4uk.shoppinglistsimple.presentation.helpers.dialog.MainDialog
import ru.ar4uk.shoppinglistsimple.ui.theme.EmptyText
import ru.ar4uk.shoppinglistsimple.ui.theme.GrayLight

@Composable
fun ShoppingListScreen(
    viewModel: ShoppingListViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val itemsList = viewModel.list.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when(uiEvent) {
                is UiEvent.Navigate -> {
                    onNavigate(uiEvent.route)
                }

                else -> {}
            }
        }
    }

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(GrayLight),
        contentPadding = PaddingValues(bottom = 100.dp)
    ) {
        items(itemsList.value) {item ->
            UIShoppingListItem(item) { event ->
                viewModel.onEvent(event)
            }
        }
    }
    MainDialog(dialogController = viewModel)
    if (itemsList.value.isEmpty()) {
        androidx.compose.material.Text(
            text = "Empty",
            fontSize = 25.sp,
            color = EmptyText,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxSize()
                .wrapContentHeight()
        )
    }
}