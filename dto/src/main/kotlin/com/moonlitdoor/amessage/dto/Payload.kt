package com.moonlitdoor.amessage.dto

import com.google.gson.Gson
import com.moonlitdoor.amessage.encryption.AesCbcWithIntegrity
import timber.log.Timber

abstract class Payload {

  abstract val type: Type

  sealed class Type(val value: String) {
    object ConnectionInvite : Type("ASFVASDFSV")
    object ConnectionConfirmation : Type("BERTYHSAFDV")
    object ConnectionRejection : Type("ETGASDFVB")
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
