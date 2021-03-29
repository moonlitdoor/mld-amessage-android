package com.moonlitdoor.amessage.analytics

import android.os.Bundle

class FirebaseAnalyticsFake(
  private val logEventHandler: ((event: String, parameters: Bundle?) -> Unit)? = null,
  private val setCurrentScreenHandler: ((name: String) -> Unit)? = null,
  private val setUserIdHandler: ((userId: String) -> Unit)? = null,
  private val setUserPropertyHandler: ((key: String, value: String?) -> Unit)? = null
) : FirebaseAnalyticsWrapper {

  override fun logEvent(event: String, parameters: Bundle?) = logEventHandler?.invoke(event, parameters) ?: Unit

  override fun setCurrentScreen(name: String) = setCurrentScreenHandler?.invoke(name) ?: Unit

  override fun setUserId(userId: String) = setUserIdHandler?.invoke(userId) ?: Unit

  override fun setUserProperty(key: String, value: String?) = setUserPropertyHandler?.invoke(key, value) ?: Unit

}
