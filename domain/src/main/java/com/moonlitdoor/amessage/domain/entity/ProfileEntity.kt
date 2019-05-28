package com.moonlitdoor.amessage.domain.entity

import com.moonlitdoor.amessage.domain.model.Profile
import java.util.*

data class ProfileEntity(
  val handle: String,
  val token: String,
  val id: UUID,
  val password: UUID,
  val salt: UUID
) {
  companion object {
    fun from(profile: Profile) = ProfileEntity(
      profile.handle,
      profile.token,
      profile.id,
      profile.password,
      profile.salt
    )
  }
}
