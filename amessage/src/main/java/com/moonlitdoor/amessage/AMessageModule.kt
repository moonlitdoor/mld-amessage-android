package com.moonlitdoor.amessage

import android.content.Context
import androidx.work.WorkManager
import com.moonlitdoor.amessage.about.AboutStatistics
import com.moonlitdoor.amessage.about.Acknowledgement
import com.moonlitdoor.amessage.about.Acknowledgements
import com.moonlitdoor.amessage.resources.DateFormatterFull
import com.moonlitdoor.amessage.resources.DateFormatterLong
import com.moonlitdoor.amessage.resources.DateFormatterMedium
import com.moonlitdoor.amessage.resources.DateFormatterShort
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
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
  fun providesAboutStatisticsFlow(): Flow<AboutStatistics> = flowOf(
    AboutStatistics(
      version = BuildConfig.VERSION_NAME,
      buildDate = BuildConfig.BUILD_DATE
    )
  )

  @Singleton
  @Provides
  fun providesAcknowledgements(@ApplicationContext context: Context): Flow<Acknowledgements> =
    context.resources.getStringArray(R.array.acknowledgements_array).let {
      flowOf(
        Acknowledgements(
          it.toList().map { acknowledgement ->
            Acknowledgement.create(acknowledgement)
          }
        )
      )
    }

  @Singleton
  @DateFormatterFull
  @Provides
  fun providesDateTimeFormatterFull(@ApplicationContext context: Context): DateTimeFormatter = DateTimeFormatter
    .ofLocalizedDateTime(FormatStyle.FULL)
    .withLocale(context.resources.configuration.locales[0])
    .withZone(ZoneId.systemDefault())

  @Singleton
  @DateFormatterLong
  @Provides
  fun providesDateTimeFormatterLong(@ApplicationContext context: Context): DateTimeFormatter = DateTimeFormatter
    .ofLocalizedDateTime(FormatStyle.LONG)
    .withLocale(context.resources.configuration.locales[0])
    .withZone(ZoneId.systemDefault())

  @Singleton
  @DateFormatterMedium
  @Provides
  fun providesDateTimeFormatterMedium(@ApplicationContext context: Context): DateTimeFormatter = DateTimeFormatter
    .ofLocalizedDateTime(FormatStyle.MEDIUM)
    .withLocale(context.resources.configuration.locales[0])
    .withZone(ZoneId.systemDefault())

  @Singleton
  @DateFormatterShort
  @Provides
  fun providesDateTimeFormatterShort(@ApplicationContext context: Context): DateTimeFormatter = DateTimeFormatter
    .ofLocalizedDateTime(FormatStyle.SHORT)
    .withLocale(context.resources.configuration.locales[0])
    .withZone(ZoneId.systemDefault())
}
