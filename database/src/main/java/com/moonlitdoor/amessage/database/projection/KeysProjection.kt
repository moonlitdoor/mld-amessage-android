package com.moonlitdoor.amessage.database.projection

import com.moonlitdoor.amessage.encryption.AuthenticatedEncryptionWithAssociatedData

class KeysProjection(
  value: String = AuthenticatedEncryptionWithAssociatedData.generateSerializedKeys()
) : KeyValueProjection<String>(key = KEY, value = value) {
  companion object {
    const val KEY = "keys"
  }
}
