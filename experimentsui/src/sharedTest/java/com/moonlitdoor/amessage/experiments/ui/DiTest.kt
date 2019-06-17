package com.moonlitdoor.amessage.experiments.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.check.checkModules

@RunWith(AndroidJUnit4::class)
class DiTest : KoinTest {

  @Test
  fun checkingModules() {
    val koin = startKoin {
      androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
      modules(experimentsUiDi + testExperimentsUiDi)
    }
    koin.checkModules()
    stopKoin()
  }

}