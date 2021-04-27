package com.moonlitdoor.amessage.dto

import java.time.Instant

class ConnectionConfirmPayload(
  val confirmed: Instant?
) : Payload() {

  @Transient
  override val type: Type = Type.ConnectionConfirm

  companion object : PayLoadInflater<ConnectionConfirmPayload> {
    override fun inflate(json: String): ConnectionConfirmPayload = GSON.fromJson(json, ConnectionConfirmPayload::class.java)
  }

}
