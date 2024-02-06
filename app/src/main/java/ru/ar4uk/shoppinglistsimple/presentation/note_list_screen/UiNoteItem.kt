package ru.ar4uk.shoppinglistsimple.presentation.note_list_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.ar4uk.shoppinglistsimple.ui.theme.BlueLight
import ru.ar4uk.shoppinglistsimple.ui.theme.LightText
import ru.ar4uk.shoppinglistsimple.ui.theme.Red

@Composable
fun UiNoteItem() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 3.dp, top = 3.dp, end = 3.dp)
            .clickable {

            }
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp)
                        .weight(1f),
                    text = "Note 1",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    modifier = Modifier.padding(top = 10.dp, end = 10.dp),
                    text = "12/12/2023 13:00",
                    color = BlueLight,
                    fontSize = 12.sp
                )
            }
            Row(modifier = Modifier.fillMaxWidth()) {
                Text(
                    modifier = Modifier
                        .padding(top = 10.dp, start = 10.dp, bottom = 10.dp)
                        .weight(1f),
                    text = "dsfsdfsfsdfsdfsdfs",
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = LightText
                )
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Red
                    )
                }
            }
        }
    }
}