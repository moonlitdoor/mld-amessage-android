package com.moonlitdoor.amessage.domain.json

class ConnectionRejectionPayload : Payload() {

  @Transient
  override val type: Type = Payload.Type.ConnectioneRejection

  companion object : PayLoadInflater<ConnectionRejectionPayload> {
    override fun inflate(json: String): ConnectionRejectionPayload = GSON.fromJson(json, ConnectionRejectionPayload::class.java)
  }
}
