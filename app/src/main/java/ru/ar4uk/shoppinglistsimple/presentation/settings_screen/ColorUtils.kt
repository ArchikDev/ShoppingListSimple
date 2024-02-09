package ru.ar4uk.shoppinglistsimple.presentation.settings_screen

import androidx.compose.ui.graphics.Color
import ru.ar4uk.shoppinglistsimple.ui.theme.GreenLight
import ru.ar4uk.shoppinglistsimple.ui.theme.Red
import ru.ar4uk.shoppinglistsimple.ui.theme.Yellow

object ColorUtils {
    val colorList = listOf(
        "#2480FF",
        "#AC24C4",
        "#24C467",
        "#24C4AC",
        "#C49424",
        "#C42424",
        "#55533B",
        "#3B554B",
        "#55423B",
        "#91AEF7",
    )

    fun getProgressColor(progress: Float): Color {
        return when(progress) {
            in 0.0..0.339 -> Red
            in 0.34..0.669 -> Yellow
            in 0.67..1.0 -> GreenLight
            else -> Red
        }
    }
}