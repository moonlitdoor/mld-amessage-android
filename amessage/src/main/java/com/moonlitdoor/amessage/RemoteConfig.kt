package com.moonlitdoor.amessage

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.moonlitdoor.amessage.extensions.ignore
import java.util.*

object RemoteConfig {

  private const val INITIAL_REMOTE_CONFIG = "initial_remote_config"

  private val remoteConfig by lazy { FirebaseRemoteConfig.getInstance() }

  private val defaults = HashMap<String, Any>().also {
    it[INITIAL_REMOTE_CONFIG] = ""
  }

  fun init(finish: () -> Unit = {}) = remoteConfig.also {
    it.setDefaults(defaults)
    it.fetch().addOnCompleteListener { task ->
      if (task.isSuccessful) it.activateFetched()
      finish()
    }
  }.ignore()

  val initialRemoteConfig: String by lazy { remoteConfig.getString(INITIAL_REMOTE_CONFIG) }
}
