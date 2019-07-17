package com.moonlitdoor.amessage.network.json

import timber.log.Timber
import java.util.*

class FirebaseMessageJson private constructor(payload: Payload, connectionId: UUID, password: UUID, salt: UUID) {

  @Suppress("UNUSED")
//  private val restricted_package_name = "com.moonlitdoor.amessage"
  private var token: String? = null
  private var tokens: List<String>? = null
  private val data = Data()

  constructor(payload: Payload, connection: ConnectionJson) : this(payload, connection.connectionId, connection.token, connection.password, connection.salt)

  constructor(payload: Payload, connectionId: UUID, id: String, password: UUID, salt: UUID) : this(payload, connectionId, password, salt) {
    token = id
  }

  constructor(payload: Payload, connectionId: UUID, ids: List<String>, password: UUID, salt: UUID) : this(payload, connectionId, password, salt) {
    tokens = ids
  }

  init {
    data.id = connectionId.toString()
    data.type = payload.type.value
    Timber.i("ENCRYPT -> DECRYPT password=%s salt=%s", password, salt)
    data.payload = Payload.encrypt(payload.toString(), password.toString(), salt.toString())
  }

  private class Data {

    var type: String = ""
    var id: String? = null
    var payload: String? = null

  }

}
