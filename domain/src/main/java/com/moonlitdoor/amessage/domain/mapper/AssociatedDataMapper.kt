package com.moonlitdoor.amessage.domain.mapper

import com.moonlitdoor.amessage.database.projection.AssociatedDataProjection
import com.moonlitdoor.amessage.domain.model.AssociatedData
import com.moonlitdoor.amessage.dto.AssociatedDataDto

object AssociatedDataMapper {

  fun map(item: AssociatedDataProjection): AssociatedData = AssociatedData(item.value)

  fun mapToProjection(item: AssociatedData): AssociatedDataProjection = AssociatedDataProjection(item.value)

  fun mapToDto(item: AssociatedDataProjection): AssociatedDataDto = AssociatedDataDto(item.value)

  fun mapToDto(item: AssociatedData): AssociatedDataDto = AssociatedDataDto(item.value)
}
