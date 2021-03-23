package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.projection.PasswordProjection
import com.moonlitdoor.amessage.domain.model.Password


object PasswordMapper {

  fun map(item: PasswordProjection) = Password(item.value)

  fun map(item: Password): PasswordProjection = PasswordProjection(item.value)
}
