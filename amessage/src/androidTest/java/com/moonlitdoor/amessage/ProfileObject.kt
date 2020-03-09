package com.moonlitdoor.amessage

import android.preference.PreferenceManager
import androidx.core.content.edit
import androidx.test.platform.app.InstrumentationRegistry
import com.moonlitdoor.amessage.constants.Constants
import java.util.*

object ProfileObject {

  lateinit var handle: String
  lateinit var id: UUID
  lateinit var password: UUID
  lateinit var salt: UUID
  val token: String by lazy {
    PreferenceManager.getDefaultSharedPreferences(InstrumentationRegistry.getInstrumentation().targetContext).getString(Constants.SharedPreferences.TOKEN, "token")
  }

  init {
    PreferenceManager.getDefaultSharedPreferences(InstrumentationRegistry.getInstrumentation().targetContext).also {
      it.getString(Constants.SharedPreferences.HANDLE, null).also { e -> e?.let { h -> handle = h } } ?: it.edit(commit = true) {
        putString(
          Constants.SharedPreferences.HANDLE,
          "Device".also { h -> handle = h })
      }
      it.getString(Constants.SharedPreferences.ID, null).also { e -> e?.let { i -> id = UUID.fromString(i) } } ?: it.edit(commit = true) {
        putString(
          Constants.SharedPreferences.ID,
          UUID.randomUUID().also { i -> id = i }.toString()
        )
      }
      it.getString(Constants.SharedPreferences.PASSWORD, null).also { e -> e?.let { p -> password = UUID.fromString(p) } }
        ?: it.edit(commit = true) { putString(Constants.SharedPreferences.PASSWORD, UUID.randomUUID().also { p -> password = p }.toString()) }
      it.getString(Constants.SharedPreferences.SALT, null).also { e -> e?.let { s -> salt = UUID.fromString(s) } }
        ?: it.edit(commit = true) { putString(Constants.SharedPreferences.SALT, UUID.randomUUID().also { s -> salt = s }.toString()) }
      it.edit(commit = true) { putInt(Constants.SharedPreferences.VERSION_CODE, Int.MAX_VALUE) }
    }
  }

}