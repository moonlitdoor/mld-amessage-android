package com.moonlitdoor.amessage.analytics

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

internal class FirebaseAnalyticsLive(context: Context, private val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)) : FirebaseAnalyticsWrapper {
  override fun logEvent(event: String, parameters: Bundle?) = analytics.logEvent(event, parameters)
}

