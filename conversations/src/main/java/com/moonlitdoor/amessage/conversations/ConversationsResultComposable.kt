package com.moonlitdoor.amessage.conversations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.moonlitdoor.amessage.domain.model.AssociatedData
import com.moonlitdoor.amessage.domain.model.Conversation
import com.moonlitdoor.amessage.domain.model.Id
import com.moonlitdoor.amessage.domain.model.Keys
import com.moonlitdoor.amessage.routes.Routes
import timber.log.Timber
import java.time.Instant

@Composable
fun ConversationsResult(navHostController: NavHostController, viewState: ConversationsViewState.Result) {
  Timber.d("ConversationsResult Composable")
  LazyColumn {
    items(viewState.items) {
      Timber.d("$it")
      Box(
        modifier = Modifier
          .fillMaxWidth()
          .wrapContentHeight()
          .clickable {
            navHostController.navigate(Routes.Conversation(it.conversationId.value).route)
          }
          .padding(8.dp)
      ) {
        Text(text = it.title ?: "TODO: What is the default title", Modifier.padding(8.dp))
      }
    }
  }
}

@Preview(showSystemUi = true)
@Composable
fun ConnectionsResultPreview() {
  val navHostController = rememberNavController()
  val viewState = ConversationsViewState.Result(
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
  ConversationsResult(navHostController, viewState)
}
