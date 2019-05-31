package com.moonlitdoor.amessage.network.json

class ConnectionRejectionPayload : Payload() {

  @Transient
  override val type: Type = Payload.Type.ConnectionRejection

  companion object : PayLoadInflater<ConnectionRejectionPayload> {
    override fun inflate(json: String): ConnectionRejectionPayload = GSON.fromJson(json, ConnectionRejectionPayload::class.java)
  }
}
