package com.moonlitdoor.amessage.experiments

import android.content.SharedPreferences
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import java.util.*
import javax.inject.Inject


data class Experiment<T : Enum<T>> internal constructor(val key: String, private val c: Class<T>, val defaultValue: T) {


  class InjectableWrapper {
    @Inject
    lateinit var remoteConfig: FirebaseRemoteConfigWrapper
    @Inject
    lateinit var sharedPreferences: SharedPreferences
  }

  private val wrapper: InjectableWrapper = ExperimentsDI.get().inject(InjectableWrapper())

  enum class BOOLEAN {
    FALSE, TRUE
  }

  private constructor(key: String, c: Class<T>, defaultValue: T, title: String? = null, description: String? = null) : this(key, c, defaultValue) {
    this.title = title
    this.description = description
  }

  private constructor(key: String, c: Class<T>, defaultValue: T, @StringRes title: Int = 0, @StringRes description: Int = 0) : this(key, c, defaultValue) {
    this.title = ExperimentsDI.get().resources().getString(title)
    this.description = ExperimentsDI.get().resources().getString(description)
  }

  val id = "com.moonlitdoor.amessage.experiment.$key"

  var title: String? = null
  var description: String? = null
  val remoteValue: String
    get() = wrapper.remoteConfig.getString(key).toUpperCase(Locale.ROOT)

  var localValue: String
    get() = wrapper.sharedPreferences.getString(id, REMOTE) ?: REMOTE
    set(value) {
      wrapper.sharedPreferences.edit().putString(id, value).apply()
    }

  val options: List<String> = listOf(REMOTE) + c.enumConstants.map { it.name.toUpperCase(Locale.ROOT) }

  val value: T
    get() = c.enumConstants.asList().find { if (localValue == REMOTE) remoteValue == it.name else localValue == it.name } ?: defaultValue

  companion object {
    @VisibleForTesting
    internal const val REMOTE = "REMOTE"

    internal operator fun invoke(key: String) = Experiment(key, BOOLEAN.FALSE)

    internal operator fun invoke(key: String, defaultValue: BOOLEAN) = Experiment(key, BOOLEAN::class.java, defaultValue)

    internal operator fun invoke(key: String, title: String? = null, description: String? = null) = Experiment(key, BOOLEAN::class.java, BOOLEAN.FALSE, title, description)

    operator fun invoke(key: String, @StringRes title: Int = 0, @StringRes description: Int = 0) = Experiment(key, BOOLEAN::class.java, BOOLEAN.FALSE, title, description)
  }

}
