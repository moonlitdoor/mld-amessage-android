package com.moonlitdoor.amessage

import android.app.Application
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

object Analytics {

  private lateinit var analytics: FirebaseAnalytics

  fun init(application: Application) {
    analytics = FirebaseAnalytics.getInstance(application)
  }

  fun sendCustomEvent() {
    val params = Bundle()
    params.putString("custom_event_param1", "foo")
    params.putString("custom_event_param2", "bar")
    analytics.logEvent("custom_event", params)
  }
}
