package com.moonlitdoor.amessage.analytics

import android.content.Context
import android.os.Bundle
import androidx.annotation.Keep
import androidx.annotation.VisibleForTesting

@Keep
object Analytics {

  @VisibleForTesting
  internal lateinit var analytics: FirebaseAnalyticsWrapper

  fun init(context: Context, userId: String, a: FirebaseAnalyticsWrapper = FirebaseAnalyticsLive(context)) {
    analytics = a
    analytics.setUserId(userId)
  }

  fun sendCustomEvent() {
    val params = Bundle()
    params.putString("custom_event_param1", "foo")
    params.putString("custom_event_param2", "bar")
    analytics.logEvent("custom_event", params)
  }
}
