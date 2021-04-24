package com.moonlitdoor.amessage

import android.app.Activity
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.google.accompanist.insets.ProvideWindowInsets

@Composable
fun EdgeToEdgeContent(content: @Composable () -> Unit) {
  val view = LocalView.current
  val window = (LocalContext.current as Activity).window
  val statusBarColor = MaterialTheme.colors.primaryVariant
  val statusBarAndroidColor = android.graphics.Color.valueOf(
    statusBarColor.red,
    statusBarColor.green,
    statusBarColor.blue,
    statusBarColor.alpha
  )
  val navigationBarColor = MaterialTheme.colors.primary
  val navigationBarAndroidColor = android.graphics.Color.valueOf(
    navigationBarColor.red,
    navigationBarColor.green,
    navigationBarColor.blue,
    navigationBarColor.alpha
  )
  window.statusBarColor = statusBarAndroidColor.toArgb()
  window.navigationBarColor = navigationBarAndroidColor.toArgb()
  val insetsController = remember(view, window) {
    WindowCompat.getInsetsController(window, view)
  }
  insetsController?.run {
    isAppearanceLightNavigationBars = false // isLightTheme
    isAppearanceLightStatusBars = false // isLightTheme
  }
  ProvideWindowInsets(content = content)
}
