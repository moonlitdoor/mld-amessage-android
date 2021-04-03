package com.moonlitdoor.amessage.dto

class ConnectionConfirmationPayload : Payload() {

  @Transient
  override val type: Type = Payload.Type.ConnectionConfirmation

  companion object : PayLoadInflater<ConnectionConfirmationPayload> {
    override fun inflate(json: String): ConnectionConfirmationPayload = GSON.fromJson(json, ConnectionConfirmationPayload::class.java)
  }

}
