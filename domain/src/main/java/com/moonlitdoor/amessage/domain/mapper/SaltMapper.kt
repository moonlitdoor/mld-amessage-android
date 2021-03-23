package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.projection.SaltProjection
import com.moonlitdoor.amessage.domain.model.Salt


object SaltMapper {

  fun map(item: SaltProjection) = Salt(item.value)

  fun map(item: Salt): SaltProjection = SaltProjection(item.value)
}
