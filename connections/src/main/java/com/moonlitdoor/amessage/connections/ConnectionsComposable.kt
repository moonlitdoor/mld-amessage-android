package com.moonlitdoor.amessage.connections

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Connections(navHostController: NavHostController, viewModel: ConnectionsViewModel) {
  Text(text = "Hello, Connections is my name!: ${viewModel.repository}")
}

@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
  MaterialTheme {
    val navHostController = rememberNavController()
    val viewModel: ConnectionsViewModel = viewModel()
    Connections(navHostController, viewModel)
  }
}
