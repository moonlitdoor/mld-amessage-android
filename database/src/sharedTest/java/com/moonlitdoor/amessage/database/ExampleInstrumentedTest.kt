package com.moonlitdoor.amessage.database

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
  @Test
  fun useAppContext() {
    assertEquals("com.moonlitdoor.amessage.database.test", InstrumentationRegistry.getInstrumentation().targetContext.packageName)
    assertEquals("com.moonlitdoor.amessage.database.test", InstrumentationRegistry.getInstrumentation().context.packageName)
  }
}