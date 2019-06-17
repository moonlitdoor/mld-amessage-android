package com.moonlitdoor.amessage.experiments.ui

import android.preference.PreferenceManager
import com.moonlitdoor.amessage.experiments.FirebaseRemoteConfigWrapper
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val testExperimentsUiDi = listOf(module {
  single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
  single(override = true) {
    object : FirebaseRemoteConfigWrapper {
      override fun setDefaults(defaults: Map<String, Any>) {}
      override fun getString(key: String): String = ""
    } as FirebaseRemoteConfigWrapper
  }
})