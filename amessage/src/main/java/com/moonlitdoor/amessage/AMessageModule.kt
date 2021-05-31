package com.moonlitdoor.amessage

import android.content.Context
import androidx.work.WorkManager
import com.moonlitdoor.amessage.about.AboutStatistics
import com.moonlitdoor.amessage.resources.DateFormatterFull
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.flowOf
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AMessageModule {

  @Singleton
  @Provides
  fun providesWorkManager(@ApplicationContext context: Context): WorkManager {
    return WorkManager.getInstance(context)
  }

  @Singleton
  @Provides
  fun providesAboutStatisticsFlow() = flowOf(
    AboutStatistics(
      version = BuildConfig.VERSION_NAME,
      buildDate = BuildConfig.BUILD_DATE
    )
  )

  @Singleton
  @DateFormatterFull
  @Provides
  fun providesDateTimeFormatterFull(@ApplicationContext context: Context) = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)
    .withLocale(context.resources.configuration.locales[0])
    .withZone(ZoneId.systemDefault())
}
