package ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.ar4uk.shoppinglistsimple.R
import ru.ar4uk.shoppinglistsimple.presentation.add_item_screen.AddItemEvent
import ru.ar4uk.shoppinglistsimple.presentation.add_item_screen.AddItemViewModel
import ru.ar4uk.shoppinglistsimple.presentation.add_item_screen.UiAddItem
import ru.ar4uk.shoppinglistsimple.presentation.helpers.UiEvent
import ru.ar4uk.shoppinglistsimple.presentation.helpers.dialog.MainDialog
import ru.ar4uk.shoppinglistsimple.ui.theme.BlueLight
import ru.ar4uk.shoppinglistsimple.ui.theme.DarkText
import ru.ar4uk.shoppinglistsimple.ui.theme.EmptyText
import ru.ar4uk.shoppinglistsimple.ui.theme.GrayLight

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AddItemScreen(
    viewModel: AddItemViewModel = hiltViewModel()
) {
    val scaffoldState = rememberScaffoldState()
    val itemsList = viewModel.itemsList?.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when(uiEvent) {
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        uiEvent.message
                    )
                }

                else -> {}
            }
        }
    }

    Scaffold(scaffoldState = scaffoldState, snackbarHost = {
        SnackbarHost(hostState = scaffoldState.snackbarHostState) { data ->
            Snackbar(
                snackbarData = data,
                backgroundColor = BlueLight
            )
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(GrayLight)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        modifier = Modifier.weight(1f),
                        value = viewModel.itemText.value,
                        onValueChange = {
                            viewModel.onEvent(AddItemEvent.OnTextChange(it))
                        },
                        label = {
                            Text(
                                text = "New item",
                                fontSize = 12.sp
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            focusedIndicatorColor = BlueLight,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            color = DarkText
                        ),
                        singleLine = true
                    )
                    IconButton(onClick = {
                        viewModel.onEvent(AddItemEvent.OnItemSave)
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.fl_add_icon),
                            contentDescription = "Add"
                        )
                    }
                }
            }
            LazyColumn(modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 5.dp,
                    end = 5.dp
                )
            ) {
                if (itemsList != null) {
                    items(itemsList.value) {item ->
                        UiAddItem(item = item, onEvent = { event ->
                            viewModel.onEvent(event)
                        })

                    }
                }
            }
        }
        MainDialog(dialogController = viewModel)
        if (itemsList?.value?.isEmpty() == true) {
            Text(
                text = "Empty",
                fontSize = 25.sp,
                textAlign = TextAlign.Center,
                color = EmptyText,
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentHeight()
            )
        }
    }
}