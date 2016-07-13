package com.moonlitdoor.amessage

import androidx.test.InstrumentationRegistry
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

  @SmokeTest
  @LargeTest
  @Test
  fun useAppContext() {
    val appContext = InstrumentationRegistry.getTargetContext()
    assertEquals("com.moonlitdoor.amessage", appContext.packageName)
    assertEquals(App::class.java.canonicalName, appContext.applicationContext.javaClass.canonicalName)
  }

}
