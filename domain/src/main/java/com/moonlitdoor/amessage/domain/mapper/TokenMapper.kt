package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.projection.TokenProjection
import com.moonlitdoor.amessage.domain.model.Token


object TokenMapper {

  fun map(item: TokenProjection) = Token(item.value)

  fun map(item: Token): TokenProjection = TokenProjection(item.value)
}
