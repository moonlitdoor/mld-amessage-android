package com.moonlitdoor.amessage.conversation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.components.AppChrome
import com.moonlitdoor.amessage.components.Navigation
import java.util.UUID

@Composable
fun Conversation(navHostController: NavHostController, viewModel: ConversationViewModel, conversationId: UUID, setAppChrome: (appChrome: AppChrome) -> Unit) {
  setAppChrome(
    AppChrome(
      title = stringResource(id = R.string.conversation_title),
      navigation = Navigation { navHostController.popBackStack() },
    )
  )

  val conversation by viewModel.getConversation(conversationId).collectAsState(initial = null)
  Text(conversation?.title ?: "")
}

@Preview(showSystemUi = true)
@Composable
fun ConversationPreview() {
  val navHostController = rememberNavController()
  val viewModel: ConversationViewModel = viewModel()
  Conversation(navHostController = navHostController, viewModel = viewModel, conversationId = UUID.randomUUID()) {}
}
