package com.moonlitdoor.amessage.conversation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import timber.log.Timber

@Composable
fun ConversationEmpty() {
  Timber.d("ConversationEmpty Composable")
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Text(text = stringResource(id = R.string.conversation_empty))
  }
}

@Preview(showBackground = true)
@Composable
fun ConversationEmptyPreview() {
  ConversationEmpty()
}
