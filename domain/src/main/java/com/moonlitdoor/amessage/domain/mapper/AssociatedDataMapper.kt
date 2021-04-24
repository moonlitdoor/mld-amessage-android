package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.projection.AssociatedDataProjection
import com.moonlitdoor.amessage.domain.model.AssociatedData

object AssociatedDataMapper {

  fun map(item: AssociatedDataProjection): AssociatedData = AssociatedData(item.value)

  fun map(item: AssociatedData): AssociatedDataProjection = AssociatedDataProjection(item.value)
}
