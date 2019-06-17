package com.moonlitdoor.amessage.experiments

import android.preference.PreferenceManager
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val testExperimentsDi = listOf(module {
  single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
  single(override = true) { FirebaseRemoteConfigFake() as FirebaseRemoteConfigWrapper }
})