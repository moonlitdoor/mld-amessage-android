package com.moonlitdoor.amessage.domain

import android.content.Context
import androidx.work.WorkManager
import com.moonlitdoor.amessage.database.DatabaseDI
import com.moonlitdoor.amessage.network.NetworkDI
import dagger.Module
import dagger.Provides

interface DomainDI {

  @Module(
    includes = [
      DatabaseDI.DatabaseModule::class,
      NetworkDI.NetworkModule::class,
      NetworkDI.BaseUrlModule::class
    ]
  )
  class DomainModule {

    @Provides
    fun providesWorkManager(context: Context): WorkManager = WorkManager.getInstance(context)

  }


}
