package com.moonlitdoor.amessage.conversations

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moonlitdoor.amessage.domain.model.AssociatedData
import com.moonlitdoor.amessage.domain.model.Conversation
import com.moonlitdoor.amessage.domain.model.Id
import com.moonlitdoor.amessage.domain.model.Keys
import com.moonlitdoor.amessage.routes.Routes
import timber.log.Timber
import java.time.Instant

@Composable
fun ConversationsResult(screenState: ConversationsScreenState.Result, navigate: (Routes) -> Unit) {
  Timber.d("ConversationsResult Composable")
  Scaffold(
    topBar = {
      TopAppBar(
        title = {
          Text(text = stringResource(id = R.string.conversations_title))
        },
        elevation = 12.dp,
      )
    },
    floatingActionButton = {
      FloatingActionButton(
        onClick = {
          /* TODO Create Conversation Flow navigateTo(Routes.CreateConversation) */
          Timber.w("navigateTo(Routes.CreateConversation)")
        }
      ) {
        Icon(painterResource(id = R.drawable.ic_baseline_forum_24), null)
      }
    }
  ) {
    LazyColumn {
      items(screenState.items) {
        Timber.d("$it")
        ConversationItem(conversation = it, navigate = navigate)
      }
    }
  }
}

@Preview()
@Composable
fun ConnectionsResultPreview() {
  val viewState = ConversationsScreenState.Result(
    listOf(
      Conversation(
        conversationId = Id(),
        title = "title",
        topic = "topic",
        created = Instant.now(),
        keys = Keys("keys"),
        associatedData = AssociatedData(),
      )
    )
  )
  ConversationsResult(viewState) { }
}
