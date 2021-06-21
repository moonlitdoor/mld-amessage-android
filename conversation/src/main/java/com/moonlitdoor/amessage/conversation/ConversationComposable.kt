package com.moonlitdoor.amessage.conversation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.components.Loading
import com.moonlitdoor.amessage.domain.model.Id
import com.moonlitdoor.amessage.extensions.Ensure
import timber.log.Timber
import java.util.UUID

@Composable
fun Conversation(navHostController: NavHostController, viewModel: ConversationViewModel, conversationId: UUID, showBottomBar: (Boolean) -> Unit) {
  Timber.d("Conversation Composable")
  viewModel.setConversationId(Id(conversationId))
  val viewState by viewModel.viewState.collectAsState(initial = ConversationViewState.Loading)
  viewState.let { state ->
    Ensure exhaustive when (state) {
      is ConversationViewState.Loading -> Loading("Conversation") { navHostController.popBackStack() }
      is ConversationViewState.Empty -> ConversationEmpty()
      is ConversationViewState.Result -> ConversationResult(navHostController = navHostController, state = state, showBottomBar)
    }
  }
}

@Preview(showSystemUi = true)
@Composable
fun ConversationPreview() {
  val navHostController = rememberNavController()
  val viewModel: ConversationViewModel = viewModel()
  Conversation(navHostController = navHostController, viewModel = viewModel, conversationId = UUID.randomUUID()) {}
}
