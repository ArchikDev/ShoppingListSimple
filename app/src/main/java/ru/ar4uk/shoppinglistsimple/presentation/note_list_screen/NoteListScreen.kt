package ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarResult
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.ar4uk.shoppinglistsimple.presentation.helpers.UiEvent
import ru.ar4uk.shoppinglistsimple.presentation.helpers.dialog.MainDialog
import ru.ar4uk.shoppinglistsimple.presentation.note_list_screen.NoteListEvent
import ru.ar4uk.shoppinglistsimple.presentation.note_list_screen.NoteListViewModel
import ru.ar4uk.shoppinglistsimple.presentation.note_list_screen.UiNoteItem
import ru.ar4uk.shoppinglistsimple.ui.theme.BlueLight
import ru.ar4uk.shoppinglistsimple.ui.theme.EmptyText
import ru.ar4uk.shoppinglistsimple.ui.theme.GrayLight
import ru.ar4uk.shoppinglistsimple.ui.theme.Red

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun NoteListScreen(
    viewModel: NoteListViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val itemsList = viewModel.noteList.collectAsState(initial = emptyList())

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { uiEvent ->
            when(uiEvent) {
                is UiEvent.Navigate -> {
                    onNavigate(uiEvent.route)
                }

                is UiEvent.ShowSnackBar -> {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = uiEvent.message,
                        actionLabel = "Undone"
                    )

                    // Если нажали на snackbar
                    if (result == SnackbarResult.ActionPerformed) {
                        viewModel.onEvent(NoteListEvent.UnDoneDeleteItem)
                    }
                }

                else -> {}
            }
        }
    }

    Scaffold(scaffoldState = scaffoldState, snackbarHost = {
        SnackbarHost(hostState = scaffoldState.snackbarHostState) { data ->
            Snackbar(
                snackbarData = data,
                backgroundColor = BlueLight,
                modifier = Modifier.padding(bottom = 100.dp)
            )
        }
    }) {
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .background(GrayLight),
            contentPadding = PaddingValues(bottom = 100.dp)
        ) {
            items(itemsList.value) {item ->
                UiNoteItem(item) { event ->
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


}