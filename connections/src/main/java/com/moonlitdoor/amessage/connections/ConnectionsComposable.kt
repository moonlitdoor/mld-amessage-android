package com.moonlitdoor.amessage.connections

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Connections(name: String) {
  Text(text = "Hello, $name is my name!")
}

@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
  Connections("Android")
}
