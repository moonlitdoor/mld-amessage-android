package com.moonlitdoor.amessage.database.view

import androidx.room.DatabaseView
import com.moonlitdoor.amessage.database.projection.AssociatedDataProjection
import com.moonlitdoor.amessage.database.projection.HandleProjection
import com.moonlitdoor.amessage.database.projection.IdProjection
import com.moonlitdoor.amessage.database.projection.KeysProjection
import com.moonlitdoor.amessage.database.projection.PasswordProjection
import com.moonlitdoor.amessage.database.projection.SaltProjection
import com.moonlitdoor.amessage.database.projection.TokenProjection

@DatabaseView(
  viewName = "profile",
  value = """SELECT 
  (SELECT value FROM key_value WHERE `key` = '${HandleProjection.KEY}') AS handle,
  (SELECT value FROM key_value WHERE `key` = '${TokenProjection.KEY}') AS token,
  (SELECT value FROM key_value WHERE `key` = '${IdProjection.KEY}') AS id,
  (SELECT value FROM key_value WHERE `key` = '${KeysProjection.KEY}') AS keys,
  (SELECT value FROM key_value WHERE `key` = '${AssociatedDataProjection.KEY}') AS associatedData,
  (SELECT value FROM key_value WHERE `key` = '${PasswordProjection.KEY}') AS password,
  (SELECT value FROM key_value WHERE `key` = '${SaltProjection.KEY}') AS salt"""
)
data class ProfileView(
  val handle: HandleProjection,
  val token: TokenProjection,
  val id: IdProjection,
  val keys: KeysProjection,
  val associatedData: AssociatedDataProjection,
  val password: PasswordProjection,
  val salt: SaltProjection,
)
