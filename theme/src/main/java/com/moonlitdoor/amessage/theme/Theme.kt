package com.moonlitdoor.amessage.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
  primary = Colors.Purple200,
  primaryVariant = Colors.Purple700,
  secondary = Colors.Teal200
)

private val LightColorPalette = lightColors(
  primary = Colors.Purple500,
  primaryVariant = Colors.Purple700,
  secondary = Colors.Teal200,

/* Overridable */
//  onPrimary = Color.White,
//  background = Color.White,
//  surface = Purple700,
//  onSecondary = Color.Black,
//  onBackground = Color.Black,
//  onSurface = Color.Black,
)

@Composable
fun AMessageTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
  val colors = if (darkTheme) {
    DarkColorPalette
  } else {
    LightColorPalette
  }

  MaterialTheme(
    colors = colors,
    typography = Typography,
    shapes = Shapes,
    content = content
  )
}

// AmTheme.Deep
// AmTheme.Cool
// AmTheme.Harvest
// AmTheme.Dark
// AmTheme.Monster
// AmTheme.Pink
