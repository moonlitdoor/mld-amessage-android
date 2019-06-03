package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.entity.ProfileEntity
import com.moonlitdoor.amessage.domain.model.Profile

object ProfileMapper {

  fun toEntity(profile: Profile) = ProfileEntity(
    profile.handle,
    profile.token,
    profile.id,
    profile.password,
    profile.salt
  )

}