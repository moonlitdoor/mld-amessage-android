package com.moonlitdoor.amessage.conversations

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun Conversations(navHostController: NavHostController) {
  val viewModel: ConversationsViewModel = viewModel()
  Text(text = "Hello, my name is Conversation!: $viewModel")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MaterialTheme {
    val navHostController = rememberNavController()
    Conversations(navHostController)
  }

}
