package com.moonlitdoor.amessage.root

import android.app.Application
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RootTest {

  @Test(expected = UninitializedPropertyAccessException::class)
  fun givenRoot_whenNotInitialized_thenThrowsException() {
    Root.get()
  }

  @Test
  fun givenRoot_whenInitialized_thenReturnsAContext() {
    Root.init(InstrumentationRegistry.getInstrumentation().targetContext as Application)
    assertNotNull(Root.get())
  }

}