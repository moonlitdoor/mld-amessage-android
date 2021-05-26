package com.moonlitdoor.amessage.conversation

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.domain.model.AssociatedData
import com.moonlitdoor.amessage.domain.model.Keys
import timber.log.Timber
import java.time.Instant

@Composable
fun ConversationResult(navHostController: NavHostController, state: ConversationViewState.Result, showBottomBar: (Boolean) -> Unit) {
  Timber.d("ConversationResult Composable")
  showBottomBar(false)
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(text = state.item.title ?: stringResource(id = R.string.conversation_title))
        },
        elevation = 12.dp,
        navigationIcon = {
          IconButton(onClick = { navHostController.popBackStack() }) {
            Icon(
              imageVector = Icons.Filled.ArrowBack,
              contentDescription = stringResource(R.string.connect_ok)
            )
          }
        },
      )
    },
  ) {

    Text(state.item.title ?: "")
  }
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
