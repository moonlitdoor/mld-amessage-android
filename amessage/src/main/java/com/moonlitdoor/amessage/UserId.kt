package com.moonlitdoor.amessage

import android.content.Context
import androidx.preference.PreferenceManager
import java.util.*

object UserId {

  private const val RANDOM_USER_ID = "com.moonlitdoor.amessage.RANDOM_USER_ID"

  lateinit var value: String

  fun init(context: Context) {
    PreferenceManager.getDefaultSharedPreferences(context).let {
      value = it.getString(RANDOM_USER_ID, null) ?: UUID.randomUUID().toString().also { id -> it.edit().putString(RANDOM_USER_ID, id).apply() }
    }
  }
}
