package com.moonlitdoor.amessage.dto

import java.time.Instant
import java.util.UUID

data class ConnectionDto(
  val id: Long = 0,
  val connectionId: UUID,
  val profileId: UUID,
  val token: String,
  val handle: String,
  val associatedData: AssociatedDataDto,
  val keys: KeysDto,
  val scanned: Instant,
  val confirmed: Instant?,
)
