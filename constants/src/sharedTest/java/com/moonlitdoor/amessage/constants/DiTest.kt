package com.moonlitdoor.amessage.constants

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.koinApplication
import org .koin.test.check.checkModules

@RunWith(AndroidJUnit4::class)
class DiTest {

  @Test
  fun checkingModules() {
    koinApplication {
      androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
      modules(constantsDi)
    }.checkModules()
  }

}