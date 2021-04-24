package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.projection.IdProjection
import com.moonlitdoor.amessage.domain.model.Id

object IdMapper {

  fun map(item: IdProjection) = Id(item.value)

  fun map(item: Id): IdProjection = IdProjection(item.value)
}
