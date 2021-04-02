package com.moonlitdoor.amessage.conversations

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.components.ActionItem
import com.moonlitdoor.amessage.routes.Routes

@Composable
fun Conversations(navHostController: NavHostController, viewModel: ConversationsViewModel, setCurrentActions: (actionItems: List<ActionItem>) -> Unit) {

  setCurrentActions(listOf())

  val handleIsSet: Boolean by viewModel.isHandleSet.collectAsState(initial = true)
  if (!handleIsSet) {
    navHostController.navigate(Routes.Handle.route) {
      launchSingleTop = true
    }
  } else {
    Text(text = "Hello, my name is Conversation!: ${viewModel.repository}")
  }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
  MaterialTheme {
    val navHostController = rememberNavController()
    val viewModel: ConversationsViewModel = viewModel()
    Conversations(navHostController, viewModel) {}
  }

}
