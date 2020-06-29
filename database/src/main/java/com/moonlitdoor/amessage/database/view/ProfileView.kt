package com.moonlitdoor.amessage.database.view

import androidx.room.DatabaseView
import java.util.*

@DatabaseView(
  viewName = "profile",
  value = """SELECT 
  (SELECT value FROM key_value WHERE `key` = 'handle') AS handle,
  (SELECT value FROM key_value WHERE `key` = 'token') AS token,
  (SELECT value FROM key_value WHERE `key` = 'id') AS id,
  (SELECT value FROM key_value WHERE `key` = 'password') AS password,
  (SELECT value FROM key_value WHERE `key` = 'salt') AS salt"""
)
data class ProfileView(
  val handle: String,
  val token: String,
  val id: UUID,
  val password: UUID,
  val salt: UUID
)
