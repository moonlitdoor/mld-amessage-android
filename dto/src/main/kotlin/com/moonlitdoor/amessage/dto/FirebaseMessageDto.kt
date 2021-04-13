package com.moonlitdoor.amessage.dto

import java.util.*

class FirebaseMessageDto private constructor(payload: Payload, connectionId: UUID, keys: KeysDto, associatedData: AssociatedDataDto) {

  @Suppress("UNUSED")
//  private val restricted_package_name = "com.moonlitdoor.amessage"
  private var token: String? = null
  private var tokens: List<String>? = null
  private val data = Data()
  private val fcm_options = FcmOptions()

  constructor(payload: Payload, connection: ConnectionJson) : this(payload, connection.connectionId, connection.token, connection.keys, connection.associatedData)

  constructor(payload: Payload, connectionId: UUID, id: String, keys: KeysDto, associatedData: AssociatedDataDto) : this(payload, connectionId, keys, associatedData) {
    token = id
  }

  constructor(payload: Payload, connectionId: UUID, ids: List<String>, keys: KeysDto, associatedData: AssociatedDataDto) : this(payload, connectionId, keys, associatedData) {
    tokens = ids
  }

  init {
    data.id = connectionId.toString()
    data.type = payload.type.value
    data.payload = Payload.encrypt(payload.toString(), keys, associatedData)
    fcm_options.analytics_label = payload.type.value
  }

  private class Data {

    var type: String = ""
    var id: String? = null
    var payload: String? = null

  }

  private class FcmOptions {

    var analytics_label: String = ""

  }

}
