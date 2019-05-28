package com.moonlitdoor.amessage

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleSharedTest {

  @Test
  fun useAppContext() {
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    assertEquals(if (BuildConfig.DEBUG) "com.moonlitdoor.amessage.beta" else "com.moonlitdoor.amessage", appContext.packageName)
  }
}