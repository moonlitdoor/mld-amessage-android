package com.moonlitdoor.amessage.dto

import java.time.Instant
import java.util.UUID

class ConnectionInvitePayload(
  val handle: String,
  val token: String,
  val connectionId: UUID,
  val associatedData: AssociatedDataDto,
  val keys: KeysDto,
  val scanned: Instant,
  val confirmed: Instant? = null
) : Payload() {

  @Transient
  override val type: Type = Type.ConnectionInvite

  companion object : PayLoadInflater<ConnectionInvitePayload> {
    override fun inflate(json: String): ConnectionInvitePayload = GSON.fromJson(json, ConnectionInvitePayload::class.java)
  }

}
