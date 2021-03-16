package com.moonlitdoor.amessage.conversations

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Conversations(name: String) {
  Text(text = "Hello, my name is $name!")
}

@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
  Conversations("Android")
}
