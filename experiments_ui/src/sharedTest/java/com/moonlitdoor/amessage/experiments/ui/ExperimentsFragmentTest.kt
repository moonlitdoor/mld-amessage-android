package com.moonlitdoor.amessage.experiments.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ExperimentsFragmentTest {

  @Before
  fun setup() {
  }

  @After
  fun teardown() {
  }

  @Ignore("DataBindingAdapter is not available in module libs")
  @Test
  fun useAppContext() {

    assertTrue(true)
//    val appContext = InstrumentationRegistry.getInstrumentation().targetContext
//    assertEquals("com.moonlitdoor.amessage.experiments.ui.test", appContext.packageName)

//    val scenario = launchFragmentInContainer<ExperimentsFragment>()
//    onView(withId(R.id.text)).check(matches(withText("Hello World!")))

  }

}
