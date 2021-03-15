package com.moonlitdoor.amessage

import android.content.Context
//import androidx.preference.PreferenceManager
import java.util.*

object UserId {

  private const val RANDOM_USER_ID = "com.moonlitdoor.amessage.random.user.id"

  lateinit var value: UUID

  fun init(context: Context) {
//    PreferenceManager.getDefaultSharedPreferences(context).let {
//      value = it.getString(RANDOM_USER_ID, null)?.let { id ->
//        UUID.fromString(id)
//      } ?: UUID.randomUUID().also { id -> it.edit().putString(RANDOM_USER_ID, id.toString()).apply() }
//    }
  }
}
