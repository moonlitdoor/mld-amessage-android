package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.projection.KeysProjection
import com.moonlitdoor.amessage.domain.model.Keys
import com.moonlitdoor.amessage.dto.KeysDto

object KeysMapper {

  fun map(item: KeysProjection): Keys = Keys(item.value)

  fun map(item: KeysDto): Keys = Keys(item.value)

  fun mapToProjection(item: Keys): KeysProjection = KeysProjection(item.value)

  fun mapToProjection(item: KeysDto): KeysProjection = KeysProjection(item.value)

  fun mapToDto(keys: Keys): KeysDto = KeysDto(keys.value)

  fun mapToDto(keys: KeysProjection): KeysDto = KeysDto(keys.value)
}
