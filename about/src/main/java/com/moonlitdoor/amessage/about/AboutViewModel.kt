package com.moonlitdoor.amessage.about

import androidx.lifecycle.ViewModel
import com.moonlitdoor.amessage.domain.repository.AboutRepository
import com.moonlitdoor.amessage.resources.DateFormatterLong
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import timber.log.Timber
import java.time.Instant
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
  repository: AboutRepository,
  statistics: Flow<AboutStatistics>,
  acknowledgements: Flow<Acknowledgements>,
  @DateFormatterLong formatter: DateTimeFormatter
) : ViewModel() {

  val screenState = combine(repository.getKeyValues(), statistics, acknowledgements) { keyValues, stats, acks ->
    keyValues.map { it.key to it.value }.toMap().run {
      AboutScreenState.Loaded(
        version = stats.version,
        buildDate = formatter.format(Instant.ofEpochMilli(stats.buildDate.toLong())),
        privacyPolicyUrl = get(PRIVACY_POLICY_URL) ?: "".also { Timber.e(RuntimeException("Privacy Policy URL Not Found")) },
        termsOfUseUrl = get(TERMS_OF_USE_URL) ?: "".also { Timber.e(RuntimeException("Terms Of Use URL Not Found")) },
        websiteUrl = get(WEBSITE_URL) ?: "".also { Timber.e(RuntimeException("Website URL Not Found")) },
        acknowledgements = acks,
      )
    }
  }

  companion object {
    private const val PRIVACY_POLICY_URL = "privacy-policy-url"
    private const val TERMS_OF_USE_URL = "terms-of-use-url"
    private const val WEBSITE_URL = "website-url"
  }
}
