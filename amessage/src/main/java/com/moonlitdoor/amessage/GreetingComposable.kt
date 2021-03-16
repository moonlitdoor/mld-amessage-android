package com.moonlitdoor.amessage

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.moonlitdoor.amessage.theme.AMessageTheme

@Composable
fun Greeting(name: String) {
  Text(text = "Hello $name!")
}

@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
  AMessageTheme {
    Greeting("Android")
  }
}