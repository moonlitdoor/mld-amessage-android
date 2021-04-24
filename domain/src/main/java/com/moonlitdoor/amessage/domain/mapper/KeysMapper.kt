package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.projection.KeysProjection
import com.moonlitdoor.amessage.domain.model.Keys

object KeysMapper {

  fun map(item: KeysProjection): Keys = Keys(item.value)

  fun map(item: Keys): KeysProjection = KeysProjection(item.value)
}
