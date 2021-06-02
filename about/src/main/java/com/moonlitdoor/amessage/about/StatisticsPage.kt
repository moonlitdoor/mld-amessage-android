package com.moonlitdoor.amessage.about

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import timber.log.Timber

@Composable
fun StatisticsPage(state: AboutScreenState.Data) {
  Timber.d("StatisticsPage")
  Column {
    Text(text = state.version)
    Text(text = state.buildDate)
  }
//  VERSION
//  BUILD DATE
//  Privacy Policy
}

@Preview(showBackground = true)
@Composable
fun StatisticsPagePreview() {
  MaterialTheme {
    StatisticsPage(
      AboutScreenState.Data(
        version = "version",
        buildDate = "buildDate",
        acknowledgements = emptyList(),
      )
    )
  }
}
