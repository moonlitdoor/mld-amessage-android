package com.moonlitdoor.amessage.analytics

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AnalyticsTest {

  @Test
  fun givenAnalytics_whenSendCustomEventIsCalled_thenAnEventWithParametersIsCalled() {
    val fake = FirebaseAnalyticsFake(logEventHandler = { event, parameters ->
      assertEquals("custom_event", event)
      assertEquals("foo", parameters?.getString("custom_event_param1"))
      assertEquals("bar", parameters?.getString("custom_event_param2"))
    })
    Analytics.init(InstrumentationRegistry.getInstrumentation().targetContext, "userId", fake)
    Analytics.sendCustomEvent()
  }


}