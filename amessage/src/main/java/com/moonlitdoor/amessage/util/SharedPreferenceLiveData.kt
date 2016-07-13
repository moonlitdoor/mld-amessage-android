package com.moonlitdoor.amessage.util

import android.content.SharedPreferences
import androidx.lifecycle.LiveData

abstract class SharedPreferenceLiveData<T>(val preferences: SharedPreferences, val key: String, private val default: T) : LiveData<T>(), SharedPreferences.OnSharedPreferenceChangeListener {

  override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
    if (key == this.key) {
      value = getValueFromPreferences(key, default)
    }
  }

  abstract fun getValueFromPreferences(key: String, default: T): T

  override fun onActive() {
    super.onActive()
    value = getValueFromPreferences(key, default)
    preferences.registerOnSharedPreferenceChangeListener(this)
  }

  override fun onInactive() {
    preferences.unregisterOnSharedPreferenceChangeListener(this)
    super.onInactive()
  }
}

class SharedPreferenceIntLiveData(sharedPrefs: SharedPreferences, key: String, default: Int) : SharedPreferenceLiveData<Int>(sharedPrefs, key, default) {
  override fun getValueFromPreferences(key: String, default: Int): Int = preferences.getInt(key, default)
}

class SharedPreferenceStringLiveData(sharedPrefs: SharedPreferences, key: String, default: String) : SharedPreferenceLiveData<String>(sharedPrefs, key, default) {
  override fun getValueFromPreferences(key: String, default: String): String = preferences.getString(key, default) ?: ""
}

class SharedPreferenceStringNullableLiveData(sharedPrefs: SharedPreferences, key: String, default: String?) : SharedPreferenceLiveData<String?>(sharedPrefs, key, default) {
  override fun getValueFromPreferences(key: String, default: String?): String? = preferences.getString(key, default)
}

class SharedPreferenceBooleanLiveData(sharedPrefs: SharedPreferences, key: String, default: Boolean) : SharedPreferenceLiveData<Boolean>(sharedPrefs, key, default) {
  override fun getValueFromPreferences(key: String, default: Boolean): Boolean = preferences.getBoolean(key, default)
}

class SharedPreferenceFloatLiveData(sharedPrefs: SharedPreferences, key: String, default: Float) : SharedPreferenceLiveData<Float>(sharedPrefs, key, default) {
  override fun getValueFromPreferences(key: String, default: Float): Float = preferences.getFloat(key, default)
}

class SharedPreferenceLongLiveData(sharedPrefs: SharedPreferences, key: String, default: Long) : SharedPreferenceLiveData<Long>(sharedPrefs, key, default) {
  override fun getValueFromPreferences(key: String, default: Long): Long = preferences.getLong(key, default)
}

class SharedPreferenceStringSetLiveData(sharedPrefs: SharedPreferences, key: String, default: Set<String>) : SharedPreferenceLiveData<Set<String>>(sharedPrefs, key, default) {
  override fun getValueFromPreferences(key: String, default: Set<String>): MutableSet<String> = preferences.getStringSet(key, default) ?: mutableSetOf()
}

fun SharedPreferences.liveData(key: String, default: Int) = SharedPreferenceIntLiveData(this, key, default)

fun SharedPreferences.liveData(key: String, default: String) = SharedPreferenceStringLiveData(this, key, default)

fun SharedPreferences.liveData(key: String) = SharedPreferenceStringNullableLiveData(this, key, null)

fun SharedPreferences.liveData(key: String, default: Boolean) = SharedPreferenceBooleanLiveData(this, key, default)

fun SharedPreferences.liveData(key: String, default: Float) = SharedPreferenceFloatLiveData(this, key, default)

fun SharedPreferences.liveData(key: String, default: Long) = SharedPreferenceLongLiveData(this, key, default)

fun SharedPreferences.liveData(key: String, default: Set<String>) = SharedPreferenceStringSetLiveData(this, key, default)
