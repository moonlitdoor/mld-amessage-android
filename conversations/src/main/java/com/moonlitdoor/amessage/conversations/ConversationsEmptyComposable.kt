package com.moonlitdoor.amessage.conversations

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
fun ConversationsEmpty() {
  Timber.d("Empty... ;(")
  Box(
    modifier = Modifier.fillMaxSize(),
    contentAlignment = Alignment.Center
  ) {
    Text(text = stringResource(id = R.string.conversations_none))
  }
}

@Preview(showBackground = true)
@Composable
fun ConversationsEmptyPreview() {
  ConversationsEmpty()
}
