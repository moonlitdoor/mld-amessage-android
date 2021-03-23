package com.moonlitdoor.amessage.connect

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Connect(navHostController: NavHostController, viewModel: ConnectViewModel) {
  Text(text = "Hello, I am Connect!: $viewModel")
}

@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
  MaterialTheme {
    val navHostController = rememberNavController()
    val viewModel: ConnectViewModel = viewModel()
    Connect(navHostController, viewModel)
  }
}
