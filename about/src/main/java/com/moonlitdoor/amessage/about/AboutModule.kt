package com.moonlitdoor.amessage.about

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AboutModule {

  @Singleton
  @Provides
  fun providesAcknowledgements(@ApplicationContext context: Context): Flow<Acknowledgements> = context.resources.getStringArray(R.array.about_acknowledgements_list).let {
    flowOf(
      Acknowledgements(
        it.toList().map { acknowledgement ->
          Acknowledgement.create(acknowledgement)
        }
      )
    )
  }
}
