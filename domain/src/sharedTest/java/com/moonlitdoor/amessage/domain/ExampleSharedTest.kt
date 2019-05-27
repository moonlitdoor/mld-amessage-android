package com.moonlitdoor.amessage.domain

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleSharedTest {

  @Test
  fun given_when_then() {
    val appContext = InstrumentationRegistry.getInstrumentation().context
    Assert.assertEquals("com.moonlitdoor.amessage.domain.test", appContext.packageName)
  }
}