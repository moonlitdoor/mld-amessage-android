package com.moonlitdoor.amessage.experiments

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.moonlitdoor.amessage.extensions.ignore
import timber.log.Timber

interface FirebaseRemoteConfigWrapper {

  fun setDefaults(defaults: Map<String, Any>)
  fun getString(key: String): String

  class FirebaseRemoteConfigLive private constructor() : FirebaseRemoteConfigWrapper {

    private val remoteConfig = Firebase.remoteConfig.also {
      it.setConfigSettingsAsync(
        remoteConfigSettings {
          minimumFetchIntervalInSeconds = if (BuildConfig.DEBUG) 0 else SYNC_FREQUENCY
        }
      )
      it.fetchAndActivate().addOnSuccessListener {
        Timber.i("fire success")
      }.addOnFailureListener { e ->
        Timber.i("fire failure")
        Timber.e(e)
      }.addOnCompleteListener {
        Timber.i("fire complete")
      }
    }

    override fun setDefaults(defaults: Map<String, Any>) = remoteConfig.setDefaultsAsync(defaults).ignore()

    override fun getString(key: String): String = remoteConfig.getString(key)

    companion object {
      private const val SYNC_FREQUENCY = 3600L

      fun get() = FirebaseRemoteConfigLive()
    }
  }

  companion object {

    private var INSTANCE: FirebaseRemoteConfigWrapper? = null

    fun get(firebaseRemoteConfigWrapper: FirebaseRemoteConfigWrapper? = INSTANCE): FirebaseRemoteConfigWrapper = firebaseRemoteConfigWrapper?.also { INSTANCE = it } ?: INSTANCE ?: FirebaseRemoteConfigLive.get().also { INSTANCE = it }
  }
}
