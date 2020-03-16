package com.moonlitdoor.amessage.analytics

import android.app.Activity
import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

internal class FirebaseAnalyticsLive(context: Context, private val analytics: FirebaseAnalytics = FirebaseAnalytics.getInstance(context)) : FirebaseAnalyticsWrapper {

  override fun logEvent(event: String, parameters: Bundle?) = analytics.logEvent(event, parameters)

  override fun setCurrentScreen(activity: Activity, key: String?, value: String?) = analytics.setCurrentScreen(activity, key, value)

  override fun setUserId(userId: String) = analytics.setUserId(userId)

  override fun setUserProperty(key: String, value: String?) = analytics.setUserProperty(key, value)

}

