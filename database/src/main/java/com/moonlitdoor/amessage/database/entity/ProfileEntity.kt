package com.moonlitdoor.amessage.database.entity

import java.util.*

data class ProfileEntity(
  val handle: String,
  val token: String,
  val id: UUID,
  val password: UUID,
  val salt: UUID
)
