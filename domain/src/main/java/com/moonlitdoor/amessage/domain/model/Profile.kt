package com.moonlitdoor.amessage.domain.model

import com.moonlitdoor.amessage.database.entity.ProfileEntity
import java.util.*

data class Profile(
  val handle: String,
  val token: String,
  val id: UUID,
  val password: UUID,
  val salt: UUID
) {

  constructor(parts: String) : this(parts.split("|")[0], parts.split("|")[1], UUID.fromString(parts.split("|")[2]), UUID.fromString(parts.split("|")[3]), UUID.fromString(parts.split("|")[4]))

  fun encode() = "$handle|$token|$id|$password|$salt"

  companion object {
    fun from(entity: ProfileEntity?) = entity?.let {
      Profile(
        it.handle,
        it.token,
        it.id,
        it.password,
        it.salt
      )
    }
  }
}
