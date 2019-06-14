package com.moonlitdoor.amessage.experiments


interface FirebaseRemoteConfigWrapper {

  fun setDefaults(defaults: Map<String, Any>)
  fun getString(key: String): String

}