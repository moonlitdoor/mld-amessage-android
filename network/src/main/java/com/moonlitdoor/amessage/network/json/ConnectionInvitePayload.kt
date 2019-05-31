package com.moonlitdoor.amessage.network.json

import java.util.*

data class ConnectionInvitePayload(val handle: String, val token: String, val connectionId: UUID, val password: UUID, val salt: UUID) : Payload() {

  @Transient
  override val type: Type = Payload.Type.ConnectionInvite

  override fun toString(): String = GSON.toJson(this)

  companion object : PayLoadInflater<ConnectionInvitePayload> {
    override fun inflate(json: String): ConnectionInvitePayload = GSON.fromJson(json, ConnectionInvitePayload::class.java)
  }

}
