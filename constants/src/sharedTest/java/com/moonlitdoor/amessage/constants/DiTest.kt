package com.moonlitdoor.amessage.constants

import android.preference.PreferenceManager
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.check.checkModules

@RunWith(AndroidJUnit4::class)
class DiTest {

  @Test
  fun checkingModules() {
    startKoin {
      androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
      modules(module {
        single { PreferenceManager.getDefaultSharedPreferences(androidContext()) }
      })
    }.checkModules()
    stopKoin()
  }

}