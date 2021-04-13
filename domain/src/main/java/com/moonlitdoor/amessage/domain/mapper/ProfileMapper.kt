package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.view.ProfileView
import com.moonlitdoor.amessage.domain.model.Profile


object ProfileMapper {

  fun map(profile: ProfileView) = Profile(
    handle = HandleMapper.map(profile.handle),
    token = TokenMapper.map(profile.token),
    id = IdMapper.map(profile.id),
    password = PasswordMapper.map(profile.password),
    salt = SaltMapper.map(profile.salt),
    associatedData = AssociatedDataMapper.map(profile.associatedData),
    keys = KeysMapper.map(profile.keys)
  )

}