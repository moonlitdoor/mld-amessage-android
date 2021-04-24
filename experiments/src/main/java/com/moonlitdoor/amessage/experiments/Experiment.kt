package com.moonlitdoor.amessage.experiments

import androidx.preference.PreferenceManager
import com.moonlitdoor.amessage.root.Root
import java.util.Locale

data class Experiment<T : Enum<T>> internal constructor(
  val key: String,
  var title: String? = null,
  var description: String? = null,
  private val c: Class<T>,
  val defaultValue: T,
) {

  private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Root.get())

  enum class BOOLEAN(val asBoolean: Boolean) {
    FALSE(false), TRUE(true)
  }

  val id = "com.moonlitdoor.amessage.experiment.$key"

  val remoteValue: String
    get() = FirebaseRemoteConfigWrapper.get().getString(key).toUpperCase(Locale.ROOT)

  var localValue: String
    get() = sharedPreferences.getString(id, REMOTE) ?: REMOTE
    set(value) {
      sharedPreferences.edit().putString(id, value).apply()
    }

  val options: List<String> = listOf(REMOTE) + c.enumConstants.map { it.name.toUpperCase(Locale.ROOT) }

  val value: T
    get() = c.enumConstants.asList().find { if (localValue == REMOTE) remoteValue == it.name else localValue == it.name } ?: defaultValue

  companion object {

    const val REMOTE = "REMOTE"

    operator fun invoke(key: String) = Experiment(key = key, defaultValue = BOOLEAN.FALSE)

    operator fun invoke(key: String, defaultValue: BOOLEAN) = Experiment(key = key, c = BOOLEAN::class.java, defaultValue = defaultValue)

    operator fun invoke(key: String, title: String? = null, description: String? = null) = Experiment(key, title, description, BOOLEAN::class.java, BOOLEAN.FALSE)
  }
}
