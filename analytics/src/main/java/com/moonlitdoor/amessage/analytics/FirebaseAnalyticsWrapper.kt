package com.moonlitdoor.amessage.analytics

import android.os.Bundle

interface FirebaseAnalyticsWrapper {

  fun logEvent(event: String, parameters: Bundle? = null)

  fun setCurrentScreen(name: String)

  fun setUserId(userId: String)

  fun setUserProperty(key: String, value: String? = null)

}