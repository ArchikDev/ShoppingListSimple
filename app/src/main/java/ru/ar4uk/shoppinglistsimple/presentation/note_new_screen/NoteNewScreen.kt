package ru.ar4uk.shoppinglistsimple.presentation.shopping_list_screen

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHost
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.ar4uk.shoppinglistsimple.R
import ru.ar4uk.shoppinglistsimple.presentation.helpers.UiEvent
import ru.ar4uk.shoppinglistsimple.presentation.note_new_screen.NewNoteEvent
import ru.ar4uk.shoppinglistsimple.presentation.note_new_screen.NoteNewViewModel
import ru.ar4uk.shoppinglistsimple.ui.theme.BlueLight
import ru.ar4uk.shoppinglistsimple.ui.theme.DarkText
import ru.ar4uk.shoppinglistsimple.ui.theme.GrayLight

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteNewScreen(
    viewModel: NoteNewViewModel = hiltViewModel(),
    onPopBackStack: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect {uiEvent ->
            when(uiEvent) {
                is UiEvent.PopBackStack -> {
                    onPopBackStack()
                }
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
        Box(modifier = Modifier
            .fillMaxSize()
            .background(color = GrayLight)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(5.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Row(modifier = Modifier.fillMaxWidth()) {
                        TextField(
                            value = viewModel.title,
                            modifier = Modifier.weight(1f),
                            onValueChange = {
                                viewModel.onEvent(NewNoteEvent.OnTitleChange(it))
                            },
                            label = {
                                Text(
                                    text = "Title...",
                                    fontSize = 14.sp
                                )
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = BlueLight,
                                containerColor = Color.White
                            ),
                            singleLine = true,
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color(android.graphics.Color.parseColor(viewModel.titleColor.value))
                            )
                        )
                        IconButton(onClick = {
                            viewModel.onEvent(NewNoteEvent.OnSave)
                        }) {
                            Icon(
                                painter = painterResource(id = R.drawable.icon_save),
                                contentDescription = null,
                                tint = BlueLight
                            )
                        }
                    }
                    TextField(
                        value = viewModel.description,
                        onValueChange = {
                            viewModel.onEvent(NewNoteEvent.OnDescriptionChange(it))
                        },
                        label = {
                            Text(
                                text = "Description",
                                fontSize = 14.sp
                            )
                        },
                        colors = TextFieldDefaults.textFieldColors(
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent,
                            containerColor = Color.White
                        ),
                        textStyle = TextStyle(
                            fontSize = 14.sp,
                            color = DarkText
                        )
                    )
                }
            }

        }
    }


}