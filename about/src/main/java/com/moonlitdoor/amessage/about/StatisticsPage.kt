package com.moonlitdoor.amessage.about

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.moonlitdoor.amessage.experiments.Experiments
import timber.log.Timber

@Composable
fun StatisticsPage(state: AboutScreenState.Loaded) {
  Timber.d("StatisticsPage")
  val scrollState = rememberScrollState()
  Column(
    modifier = Modifier
      .fillMaxSize()
      .verticalScroll(scrollState)
  ) {
    StatisticsItem(title = R.string.about_version, value = state.version)
    Divider()
    StatisticsItem(title = R.string.about_build_date, value = state.buildDate)
    Divider()
    if (Experiments.FEATURE_ABOUT_PRIVACY_POLICY.value.asBoolean) {
      StatisticsItem(title = R.string.about_privacy_policy, value = state.privacyPolicyUrl, type = StatisticsItemType.URL)
      Divider()
    }
    if (Experiments.FEATURE_ABOUT_TERMS_OF_USE.value.asBoolean) {
      StatisticsItem(title = R.string.about_terms_of_use, value = state.termsOfUseUrl, type = StatisticsItemType.URL)
      Divider()
    }
    StatisticsItem(title = R.string.about_email, value = R.string.about_email_value, type = StatisticsItemType.EMAIL)
    Divider()
    StatisticsItem(title = R.string.about_twitter, value = R.string.about_twitter_value, type = StatisticsItemType.TWITTER)
    Divider()
    StatisticsItem(title = R.string.about_playstore, value = R.string.about_playstore_value, type = StatisticsItemType.URL)
    Divider()
    if (Experiments.FEATURE_ABOUT_WEBSITE.value.asBoolean) {
      StatisticsItem(title = R.string.about_website, value = state.websiteUrl, type = StatisticsItemType.URL)
      Divider()
    }
    StatisticsItem(title = R.string.about_github_issues, value = R.string.about_github_issues_value, type = StatisticsItemType.URL)
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
        privacyPolicyUrl = "privacyPolicyUrl",
        termsOfUseUrl = "termsOfUseUrl",
        websiteUrl = "websiteUrl",
        acknowledgements = emptyList(),
      )
    )
  }
}
