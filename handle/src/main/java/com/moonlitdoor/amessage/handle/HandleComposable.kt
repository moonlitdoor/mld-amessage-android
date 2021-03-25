package com.moonlitdoor.amessage.handle

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Handle(navHostController: NavHostController, viewModel: HandleViewModel) {
  Text(text = "Hello, Handle is my name!: $viewModel")
}

@Preview(showBackground = false)
@Composable
fun DefaultPreview() {
  MaterialTheme {
    val navHostController = rememberNavController()
    val viewModel: HandleViewModel = viewModel()
    Handle(navHostController, viewModel)
  }
}
