package com.moonlitdoor.amessage.experiments

import android.preference.PreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val experimentsDi = listOf(module {
  single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
  single { FirebaseRemoteConfigLive.get() as FirebaseRemoteConfigWrapper }
})