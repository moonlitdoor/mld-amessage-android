package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.entity.PayloadEntity
import com.moonlitdoor.amessage.domain.model.Payload

object PayloadMapper {

  fun map(entity: PayloadEntity) = Payload(
    primary = entity.primary,
    type = entity.type,
    id = entity.id,
    cipher = entity.cipher,
  )

  fun entity(payload: Payload) = PayloadEntity(
    primary = payload.primary,
    type = payload.type,
    id = payload.id,
    cipher = payload.cipher,
  )
}
