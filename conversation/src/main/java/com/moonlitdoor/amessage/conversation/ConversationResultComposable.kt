package com.moonlitdoor.amessage.conversation

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.components.AppChrome
import com.moonlitdoor.amessage.components.Navigation
import com.moonlitdoor.amessage.domain.model.AssociatedData
import com.moonlitdoor.amessage.domain.model.Keys
import timber.log.Timber
import java.time.Instant

@Composable
fun ConversationResult(navHostController: NavHostController, state: ConversationViewState.Result, setAppChrome: (appChrome: AppChrome) -> Unit) {
  Timber.d("ConversationResult Composable")
  setAppChrome(
    AppChrome(
      title = state.item.title ?: stringResource(id = R.string.conversation_title),
      navigation = Navigation { navHostController.popBackStack() },
    )
  )
  Text(state.item.title ?: "")
}

@Preview(showBackground = true)
@Composable
fun ConversationResultPreview() {
  val navHostController = rememberNavController()
  val viewState = ConversationViewState.Result(
    com.moonlitdoor.amessage.domain.model.Conversation(
      title = "Title",
      topic = "topic",
      created = Instant.now(),
      keys = Keys("keys"),
      associatedData = AssociatedData()
    )
  )
  ConversationResult(navHostController, viewState) {}
}
