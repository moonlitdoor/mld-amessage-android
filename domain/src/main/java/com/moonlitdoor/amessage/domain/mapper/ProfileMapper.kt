package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.view.ProfileView
import com.moonlitdoor.amessage.domain.model.Profile


object ProfileMapper {

  fun map(profile: ProfileView) = Profile(
    handle = profile.handle,
    token = profile.token,
    id = profile.id,
    password = profile.password,
    salt = profile.salt,
  )

}