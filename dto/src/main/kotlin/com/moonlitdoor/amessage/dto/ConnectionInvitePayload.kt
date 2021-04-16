package com.moonlitdoor.amessage.dto

import java.time.Instant
import java.util.*

data class ConnectionInvitePayload(
  val handle: String,
  val token: String,
  val connectionId: UUID,
  val associatedData: AssociatedDataDto,
  val keys: KeysDto,
  val scanned: Instant,
) : Payload() {

  @Transient
  override val type: Type = Type.ConnectionInvite

  override fun toString(): String = GSON.toJson(this)

  companion object : PayLoadInflater<ConnectionInvitePayload> {
    override fun inflate(json: String): ConnectionInvitePayload = GSON.fromJson(json, ConnectionInvitePayload::class.java)
  }

}
