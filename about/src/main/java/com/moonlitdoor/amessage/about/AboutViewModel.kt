package com.moonlitdoor.amessage.about

import androidx.lifecycle.ViewModel
import com.moonlitdoor.amessage.resources.DateFormatterFull
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class AboutViewModel @Inject constructor(statisticsFlow: Flow<AboutStatistics>, @DateFormatterFull formatter: DateTimeFormatter) : ViewModel() {

  val screenState = statisticsFlow.map {
    AboutScreenState.Data(
      version = it.version,
      buildDate = formatter.format(Instant.ofEpochMilli(it.buildDate.toLong())),
    )
  }
}
