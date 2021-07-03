package com.moonlitdoor.amessage.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import timber.log.Timber

@Composable
fun StatisticsPage(state: AboutScreenState.Loaded) {
  Timber.d("StatisticsPage")
  Column(modifier = Modifier.fillMaxSize()) {
    StatisticsItem(title = R.string.about_version, value = state.version)
    Divider()
    StatisticsItem(title = R.string.about_build_date, value = state.buildDate)
    Divider()
    StatisticsItem(title = R.string.about_privacy_policy, url = R.string.about_privacy_policy_url)
    Divider()
    StatisticsItem(title = R.string.about_terms_of_use, url = R.string.about_terms_of_use_url)
    Divider()
  }
}

@Preview(showBackground = true)
@Composable
fun StatisticsPagePreview() {
  MaterialTheme {
    StatisticsPage(
      AboutScreenState.Loaded(
        version = "version",
        buildDate = "buildDate",
        acknowledgements = emptyList(),
      )
    )
  }
}
