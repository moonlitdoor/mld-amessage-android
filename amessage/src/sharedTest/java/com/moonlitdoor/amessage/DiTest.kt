package com.moonlitdoor.amessage

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidFileProperties
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.check.checkModules

@RunWith(AndroidJUnit4::class)
class DiTest {


  @Ignore("Not Working")
  @Test
  fun checkingModules() {
    startKoin {
      androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
      androidFileProperties()
      modules(di)
    }.checkModules()
    stopKoin()
  }

}