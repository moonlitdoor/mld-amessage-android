package com.moonlitdoor.amessage

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertTrue
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ExampleSharedTest {

  @Ignore("Need to extract Firebase to make it testable")
  @Test
  fun useAppContext() {
    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
    assertTrue(listOf("com.moonlitdoor.amessage","com.moonlitdoor.amessage.beta","com.moonlitdoor.amessage.debug").contains(appContext.packageName))
  }
}