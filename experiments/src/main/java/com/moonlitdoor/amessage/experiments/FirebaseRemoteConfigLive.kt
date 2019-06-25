package com.moonlitdoor.amessage.experiments

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings

class FirebaseRemoteConfigLive private constructor() : FirebaseRemoteConfigWrapper {

  private val config = FirebaseRemoteConfig.getInstance().also {
    it.activate()
    it.setConfigSettingsAsync(FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(if (BuildConfig.DEBUG) 0 else SYNC_FREQUENCY).build())
    it.fetch().addOnCompleteListener { task ->
      if (BuildConfig.DEBUG && task.isSuccessful) it.activate()
    }
  }

  override fun setDefaults(defaults: Map<String, Any>) = config.setDefaults(defaults)

  override fun getString(key: String): String = config.getString(key)

  companion object {
    private const val SYNC_FREQUENCY = 3600L
    private val INSTANCE = FirebaseRemoteConfigLive()

    fun get() = INSTANCE
  }

}