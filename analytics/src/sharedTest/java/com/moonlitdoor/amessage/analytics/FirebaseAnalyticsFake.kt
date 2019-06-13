package com.moonlitdoor.amessage.analytics

import android.os.Bundle

class FirebaseAnalyticsFake(private val logEventHandler: ((event: String, parameters: Bundle?) -> Unit)? = null) : FirebaseAnalyticsWrapper {

  override fun logEvent(event: String, parameters: Bundle?) = logEventHandler?.invoke(event, parameters) ?: Unit

}