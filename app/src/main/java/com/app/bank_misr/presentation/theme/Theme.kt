package com.app.bank_misr.presentation.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val lightColorScheme = lightColorScheme(
  // Displayed most frequently across app's screens and components.
  primary = CelestialBlue,
  // FABs ,sliders, switches, Progress bars, Links, headlines, Highlighting selected text.
  secondary = CelestialBlue,
  // can be used to balance primary and secondary colors,
  // or bring heightened attention to an element such as an input field.
  tertiary = CelestialBlue,
  // Affect surfaces of components, such as cards, sheets, and menus.
  surface = White,
  surfaceTint = White,
  // Appears behind scrollable content.
  background = White,
  // Indicates errors in components, such as invalid text in a text field.
  error = Madder,
  // color elements that appear “on” top of surfaces that use the following colors: a primary color,
  // secondary color, surface color, background color, or error color.
  onPrimary = White,
  onSecondary = White,
  onBackground = Madder,
  onSurface = Madder,
  onTertiary = White,
)

@Composable
fun AppTheme(
  dynamicColor: Boolean = true,
  content: @Composable () -> Unit
) {
  val view = LocalView.current
  if (!view.isInEditMode) {
    val currentWindow = (view.context as? Activity)?.window
      ?: throw Exception("Not in an activity - unable to get Window reference")

    SideEffect {
      currentWindow.statusBarColor = Color.Transparent.toArgb()
      WindowCompat.getInsetsController(currentWindow, view).isAppearanceLightStatusBars = true
    }
  }

  MaterialTheme(
    colorScheme = lightColorScheme,
    content = content
  )
}