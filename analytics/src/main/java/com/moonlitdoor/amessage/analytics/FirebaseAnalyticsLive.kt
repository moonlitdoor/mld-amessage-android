package com.moonlitdoor.amessage.analytics

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent

internal class FirebaseAnalyticsLive(context: Context, private val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)) : FirebaseAnalyticsWrapper {

  override fun logEvent(event: String, parameters: Bundle?) = analytics.logEvent(event, parameters)

  override fun setCurrentScreen(name: String) = analytics.logEvent(FirebaseAnalytics.Event.SCREEN_VIEW) {
    param(FirebaseAnalytics.Param.SCREEN_NAME, name)
  }

  override fun setUserId(userId: String) = analytics.setUserId(userId)

  override fun setUserProperty(key: String, value: String?) = analytics.setUserProperty(key, value)
}
