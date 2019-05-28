package com.moonlitdoor.amessage.domain.json

import com.google.gson.Gson
import com.moonlitdoor.amessage.domain.util.AesCbcWithIntegrity
import timber.log.Timber

abstract class Payload {

  abstract val type: Type

  sealed class Type(val value: Int) {
    object ConnectionInvite : Type(1)
    object ConnectionConfirmation : Type(2)
    object ConnectioneRejection : Type(3)
  }

  companion object {

    internal val GSON = Gson()

    fun encrypt(payload: String, password: String, salt: String): String {
      Timber.d("ENCRYPTION_CHECK password=$password salt=$salt")
      return AesCbcWithIntegrity.encrypt(payload, password, salt)
    }

    fun decrypt(encryptedPayload: String, password: String, salt: String): String {
      Timber.d("DECRYPTION_CHECK password=$password salt=$salt")
      return AesCbcWithIntegrity.decrypt(encryptedPayload, password, salt)
    }
  }

}
