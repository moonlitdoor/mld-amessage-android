package com.moonlitdoor.amessage.experiments

class FirebaseRemoteConfigFake(
  private val defaultsHandler: ((defaults: Map<String, Any>) -> Unit)? = null,
  private val stringHandler: ((key: String) -> String)? = null
) : FirebaseRemoteConfigWrapper {

  override fun setDefaults(defaults: Map<String, Any>) = defaultsHandler?.invoke(defaults) ?: Unit

  override fun getString(key: String): String = stringHandler?.invoke(key) ?: ""
}
