package com.moonlitdoor.amessage.analytics

import android.app.Activity
import android.os.Bundle

interface FirebaseAnalyticsWrapper {

  fun logEvent(event: String, parameters: Bundle? = null)

  fun setCurrentScreen(activity: Activity, key: String? = null, value: String? = null)

  fun setUserId(userId: String)

  fun setUserProperty(key: String, value: String? = null)

}