package com.moonlitdoor.amessage.connections

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Connections(navHostController: NavHostController) {
  Text(text = "Hello, Connections is my name!")
}

@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
  MaterialTheme {
    val navHostController = rememberNavController()
    Connections(navHostController)
  }
}
