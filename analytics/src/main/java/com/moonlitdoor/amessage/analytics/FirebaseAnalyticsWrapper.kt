package com.moonlitdoor.amessage.analytics

import android.os.Bundle

interface FirebaseAnalyticsWrapper {

  fun logEvent(event: String, parameters: Bundle?)
}