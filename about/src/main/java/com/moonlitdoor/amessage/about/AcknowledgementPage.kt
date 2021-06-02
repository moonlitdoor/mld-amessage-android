package com.moonlitdoor.amessage.about

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import timber.log.Timber

@Composable
fun AcknowledgementPage(state: AboutScreenState.Data) {
  Timber.d("AcknowledgementPage")
  LazyColumn {
    items(state.acknowledgements) {
      Text(it.title)
    }
  }
}

@Preview(showBackground = true)
@Composable
fun AcknowledgementPagePreview() {
  MaterialTheme {
    AcknowledgementPage(
      AboutScreenState.Data(
        version = "",
        buildDate = "",
        acknowledgements = emptyList(),
      )
    )
  }
}
