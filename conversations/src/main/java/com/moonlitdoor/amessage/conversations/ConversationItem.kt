package com.moonlitdoor.amessage.conversations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.moonlitdoor.amessage.domain.model.AssociatedData
import com.moonlitdoor.amessage.domain.model.Conversation
import com.moonlitdoor.amessage.domain.model.Keys
import com.moonlitdoor.amessage.routes.Routes
import timber.log.Timber
import java.time.Instant
import java.util.UUID

@Composable
fun ConversationItem(conversation: Conversation, navigate: (Routes) -> Unit) {
  Timber.d("ConversationsItem")
  Box(
    modifier = Modifier
      .fillMaxWidth()
      .wrapContentHeight()
      .clickable {
        navigate(Routes.Conversation(conversation.conversationId.value))
      }
      .padding(8.dp)
  ) {
    Text(text = conversation.title ?: "TODO: What is the default title", Modifier.padding(8.dp))
  }
}

@Preview(showBackground = true)
@Composable
fun ConversationItemPreview() {
  ConversationItem(
    conversation = Conversation(
      title = "Title",
      topic = "topic",
      created = Instant.now(),
      keys = Keys("keys"),
      associatedData = AssociatedData(UUID.randomUUID())
    )
  ) {
  }
}
