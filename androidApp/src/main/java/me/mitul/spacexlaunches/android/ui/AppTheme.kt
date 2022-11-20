package me.mitul.spacexlaunches.android.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun AppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) darkColors(Green200) else lightColors(Red200)
    MaterialTheme(colors, content = content)
}

val Green200 = Color(0xFFA5D6A7)
val Green900 = Color(0xFF1B5E20)
val Red200 = Color(0xFFEF9A9A)
val Red900 = Color(0xFFB71C1C)
