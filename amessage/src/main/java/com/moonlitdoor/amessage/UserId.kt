package com.moonlitdoor.amessage

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import java.util.UUID

object UserId {

  private const val MLD_USER_ID = "mld.user.id"

  lateinit var value: UUID

  fun init(context: Context) {
    PreferenceManager.getDefaultSharedPreferences(context).let {
      value = it.getString(MLD_USER_ID, null)?.let { id ->
        UUID.fromString(id)
      } ?: UUID.randomUUID().also { id ->
        it.edit { putString(MLD_USER_ID, id.toString()) }
      }
    }
  }
}
