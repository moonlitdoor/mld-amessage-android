package com.moonlitdoor.amessage.experiments

import android.content.Context
import android.content.SharedPreferences
import androidx.annotation.StringRes
import androidx.annotation.VisibleForTesting
import org.koin.core.KoinComponent
import org.koin.core.get
import java.util.*


data class Experiment<T : Enum<T>>(val key: String, private val c: Class<T>, val defaultValue: T) : KoinComponent {

  private val remoteConfig: FirebaseRemoteConfigWrapper = get()
  private val sharedPreferences: SharedPreferences = get()

  enum class BOOLEAN {
    FALSE, TRUE
  }

  private constructor(key: String, c: Class<T>, defaultValue: T, title: String? = null, description: String? = null) : this(key, c, defaultValue) {
    this.title = title
    this.description = description
  }

  private constructor(key: String, c: Class<T>, defaultValue: T, @StringRes title: Int = 0, @StringRes description: Int = 0) : this(key, c, defaultValue) {
    this.title = get<Context>().getString(title)
    this.description = get<Context>().getString(description)
  }

  val id = "com.moonlitdoor.amessage.experiment.$key"

  var title: String? = null
  var description: String? = null
  val remoteValue: String
    get() = remoteConfig.getString(key).toUpperCase(Locale.ROOT)

  var localValue: String
    get() = sharedPreferences.getString(id, REMOTE) ?: REMOTE
    set(value) {
      sharedPreferences.edit().putString(id, value).apply()
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
