package com.moonlitdoor.amessage.about

import androidx.lifecycle.ViewModel
import com.moonlitdoor.amessage.resources.DateFormatterFull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import java.time.Instant
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(
  statistics: Flow<AboutStatistics>,
  acknowledgements: Flow<Acknowledgements>,
  @DateFormatterFull formatter: DateTimeFormatter
) : ViewModel() {

  val screenState = combine(statistics, acknowledgements) { stats, acks ->
    AboutScreenState.Data(
      version = stats.version,
      buildDate = formatter.format(Instant.ofEpochMilli(stats.buildDate.toLong())),
      acknowledgements = acks,
    )
  }
}
