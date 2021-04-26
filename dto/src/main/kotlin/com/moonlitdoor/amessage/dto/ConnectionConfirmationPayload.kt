package com.moonlitdoor.amessage.dto

import java.time.Instant

class ConnectionConfirmationPayload(
  val confirmed: Instant?
) : Payload() {

  @Transient
  override val type: Type = Type.ConnectionConfirmation

  companion object : PayLoadInflater<ConnectionConfirmationPayload> {
    override fun inflate(json: String): ConnectionConfirmationPayload = GSON.fromJson(json, ConnectionConfirmationPayload::class.java)
  }

}
