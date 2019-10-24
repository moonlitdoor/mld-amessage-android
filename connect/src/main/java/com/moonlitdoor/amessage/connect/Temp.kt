package com.moonlitdoor.amessage.connect

import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.material.MaterialTheme
import androidx.ui.tooling.preview.Preview

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview
@Composable
fun DefaultPreview() {
  MaterialTheme {
    Greeting("Andoid")
  }
}