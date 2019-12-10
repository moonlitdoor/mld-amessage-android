package com.moonlitdoor.amessage.experiments.ui

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin


@RunWith(AndroidJUnit4::class)
class ExperimentsFragmentTest {

  @Before
  fun setup() {
    startKoin {
      androidContext(InstrumentationRegistry.getInstrumentation().targetContext)
      modules(experimentsUiDi + testExperimentsUiDi)
    }
  }

  @After
  fun teardown() {
    stopKoin()
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
