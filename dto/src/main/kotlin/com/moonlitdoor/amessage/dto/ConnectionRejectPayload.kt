package com.moonlitdoor.amessage.dto

class ConnectionRejectPayload : PayloadDto() {

  @Transient
  override val type: Type = Type.ConnectionReject

  companion object : PayLoadInflater<ConnectionRejectPayload> {
    override fun inflate(json: String): ConnectionRejectPayload = GSON.fromJson(json, ConnectionRejectPayload::class.java)
  }
}
