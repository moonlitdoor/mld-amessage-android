package com.moonlitdoor.amessage.analytics

import android.content.Context
import android.os.Bundle
import androidx.annotation.VisibleForTesting

object Analytics {

  @VisibleForTesting
  internal lateinit var analytics: FirebaseAnalyticsWrapper

  fun init(context: Context, a: FirebaseAnalyticsWrapper = FirebaseAnalyticsLive(context)) {
    analytics = a
  }

  fun sendCustomEvent() {
    val params = Bundle()
    params.putString("custom_event_param1", "foo")
    params.putString("custom_event_param2", "bar")
    analytics.logEvent("custom_event", params)
  }
}
