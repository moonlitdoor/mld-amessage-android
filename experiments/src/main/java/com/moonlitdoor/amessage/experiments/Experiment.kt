package com.moonlitdoor.amessage.experiments

import android.preference.PreferenceManager
import androidx.annotation.StringRes
import com.moonlitdoor.amessage.resources.ResourceContainer
import com.moonlitdoor.amessage.root.Root
import java.util.*
import javax.inject.Inject

data class Experiment<T : Enum<T>> internal constructor(val key: String, private val c: Class<T>, val defaultValue: T) {

  class InjectableWrapper {
    @Inject
    lateinit var remoteConfig: FirebaseRemoteConfigWrapper
  }

  private val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(Root.get())

  enum class BOOLEAN {
    FALSE, TRUE
  }

  private constructor(key: String, c: Class<T>, defaultValue: T, title: String? = null, description: String? = null) : this(key, c, defaultValue) {
    this.title = title
    this.description = description
  }

  private constructor(key: String, c: Class<T>, defaultValue: T, @StringRes title: Int = 0, @StringRes description: Int = 0) : this(key, c, defaultValue) {
    this.title = ResourceContainer.getString(title)
    this.description = ResourceContainer.getString(description)
  }

  val id = "com.moonlitdoor.amessage.experiment.$key"

  var title: String? = null
  var description: String? = null
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

    operator fun invoke(key: String) = Experiment(key, BOOLEAN.FALSE)

    operator fun invoke(key: String, defaultValue: BOOLEAN) = Experiment(key, BOOLEAN::class.java, defaultValue)

    operator fun invoke(key: String, title: String? = null, description: String? = null) = Experiment(key, BOOLEAN::class.java, BOOLEAN.FALSE, title, description)

    operator fun invoke(key: String, @StringRes title: Int = 0, @StringRes description: Int = 0) = Experiment(key, BOOLEAN::class.java, BOOLEAN.FALSE, title, description)
  }

}
