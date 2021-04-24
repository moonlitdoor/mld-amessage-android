package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.projection.HandleProjection
import com.moonlitdoor.amessage.domain.model.Handle

object HandleMapper {

  fun map(item: HandleProjection) = Handle(item.value)

  fun map(item: Handle): HandleProjection = HandleProjection(item.value)
}
